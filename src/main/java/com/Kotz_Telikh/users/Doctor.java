package com.Kotz_Telikh.users;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.Kotz_Telikh.other.DbInfo.*;

public class Doctor extends Users {
    private final String specialty;
    private final String amka;

    public Doctor(String amka, String username, String password, String name, String surname, String specialty, String salt) {
        super(username, password, name, surname, salt);
        this.specialty = specialty;
        this.amka = amka;
    }

    // getter for specialty
    public String getSpecialty(){
        return specialty;
    }

    public String getAmka() {
        return amka;
    }

    // καταχωρηση διαθεσιμοτητας ιατρου για ραντεβου
    public String insertAvailability(String date, String starttime) {

        // na kanoume select * from appointment where date = 'date' and startslottime = 'starttime'
        // if rs.next() tote na epistrefei h synarthsh "appointment already booked" oti einai hdh kataxwrhmeno kai den kataxwrhthike
        // diaforetika tha kanei insert kanonika

        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();

            String starttime2 = starttime + ":00";
            System.out.println("Date is: " + date);
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM appointment WHERE date_= '" + date + "' AND startslottime = '" + starttime2 + "' AND doctor_amka = '" + getAmka() +"'");
            if (rs1.next()) {
                rs1.close();
                stmt.close();
                conn.close();
                return "alreadyEntered";
            }

            ResultSet rs = stmt.executeQuery("SELECT max(appointment_id) FROM appointment");
            int app_id = -1;
            if (rs.next()){
                app_id = rs.getInt(1) + 1;
            }
            rs.close();

            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date d = df.parse(starttime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE, 30);
            String endtime = df.format(cal.getTime());

            stmt.executeUpdate("INSERT INTO appointment VALUES (" + app_id + ",'" + date + "', '" + starttime + "', '" + endtime +"', '', '" + getAmka() + "', true)");
            stmt.close();
            conn.close();
        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
            return "dberror";
        }

        return "true";
    }

    // προβολη προγραμματος ραντεβου
    public StringBuilder showDates(){
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM appointment join patient on appointment.patient_amka = patient.patient_amka WHERE appointment.doctor_amka = '" + getAmka() + "' AND date_ > '2021-6-12' AND isavailable = false ORDER BY date_");
            sb.append("<tr>");
            sb.append("<td style='font-weight: bold; text-align: center;'>ID</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Date</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Time</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Patient's name</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Patient's amka</td>");
            while (rs.next()) {
                sb.append("<tr>").append("<td style='width: 30px;'>").append(rs.getString("appointment_id")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("date_")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("startslottime")).append(" to ").append(rs.getString("endslottime")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("name")).append(" ").append(rs.getString("surname")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("patient_amka")).append("</td>");
                sb2.append("<option value='").append(rs.getString("appointment_id")).append("'>").append(rs.getString("appointment_id")).append("</option>");
            }
            sb.append("SPLIT").append(sb2);
            conn.close();
            stmt.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return sb;
    }


}
