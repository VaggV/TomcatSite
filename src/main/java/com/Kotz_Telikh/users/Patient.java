package com.Kotz_Telikh.users;

import java.sql.*;

import static com.Kotz_Telikh.other.DbInfo.*;

public class Patient extends Users {
    private String amka;

    public Patient(String username, String password, String name, String surname, String amka, String salt) {
        super(username, password, name, surname, salt);
        this.amka = amka;
    }

    public Patient(String username, String password){
        super(username, password);
    }

    // getter for amka
    public String getAmka(){
        return amka;
    }

    // εγγραφη χρηστη
    public void register() {
        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO patient (patient_amka, username, hashedpassword, name, surname, salt) values ('" + getAmka() + "','" + getUsername() + "','" + getPassword() + "','" + getName() + "','" + getSurname() + "','" + getSalt() + "')");
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // αναζητηση ραντεβου για οποιονδηποτε διαθεσιμο ιατρο μιας ειδικοτητας
    public StringBuilder availableAppointments(String specialty) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM appointment join doctor on appointment.doctor_amka = doctor.doctor_amka WHERE doctor.specialty = '" + specialty + "' AND isavailable = 'true' ORDER BY date_");
            sb.append("<tr>");
            sb.append("<td style='font-weight: bold; text-align: center;'>ID</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Date</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Time</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Name</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Doctor's amka</td></tr>");
            while (rs.next()) {
                sb.append("<tr>").append("<td style='width: 30px;'>").append(rs.getString("appointment_id")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("date_")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("startslottime")).append(" to ").append(rs.getString("endslottime")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("name")).append(" ").append(rs.getString("surname")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("doctor_amka")).append("<td></tr>");
                sb2.append("<option value='").append(rs.getString("appointment_id")).append("'>").append(rs.getString("appointment_id")).append("</option>");
            }
            sb.append("(SPLIT)").append(sb2);
            conn.close();
            stmt.close();
            rs.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return sb;
    }

    // προβολη προγραμματισμενων ραντεβου
    public StringBuilder scheduledAppointments(){
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM appointment join doctor on appointment.doctor_amka = doctor.doctor_amka WHERE appointment.patient_amka = '" + getAmka() + "' AND date_ > '2021-6-12' ORDER BY date_");
            sb.append("<tr>");
            sb.append("<td style='font-weight: bold; text-align: center;'>ID</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Date</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Time</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Name</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Doctor's amka</td>");
            sb.append("<td style='font-weight: bold; text-align: center;'>Specialty</td></tr>");
            while (rs.next()) {
                sb.append("<tr>").append("<td style='width: 30px;'>").append(rs.getString("appointment_id")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("date_")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("startslottime")).append(" to ").append(rs.getString("endslottime")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("name")).append(" ").append(rs.getString("surname")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("doctor_amka")).append("</td>");
                sb.append("<td style='width:200px; text-align: center;'>").append(rs.getString("specialty")).append("</td></tr>");
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

    public void bookAppointment(int id){
        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE appointment SET isavailable = false, patient_amka = '" + getAmka() + "' WHERE appointment_id = '" + id + "'");
            conn.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public StringBuilder appointmentHistory(){
        StringBuilder sb = new StringBuilder();
        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM appointment join doctor on doctor.doctor_amka = appointment.doctor_amka WHERE appointment.patient_amka = '" + getAmka() + "' ORDER BY date_");
            sb.append("<div style='text-align:center;'>");
            while(rs.next()){
                String date = rs.getString("date_");
                String[] d = date.split("-");
                String[] dNow = dateNow.split("-");

                if (Integer.parseInt(dNow[0]) < Integer.parseInt(d[0]))
                    continue;
                else if (Integer.parseInt(dNow[0]) == Integer.parseInt(d[0])) {
                    if (Integer.parseInt(dNow[1]) < Integer.parseInt(d[1])) {
                        continue;
                    } else if (Integer.parseInt(dNow[1]) == Integer.parseInt(d[1])){
                        if (Integer.parseInt(dNow[2]) < Integer.parseInt(d[2]))
                            continue;
                    }
                }

                sb.append("<span style='text-align:center;font-weight:bold;'> Appointment id: </span>").append(rs.getString("appointment_id")).append("<br>");
                sb.append("<span style='text-align:center;font-weight:bold;'> Date: </span>").append(rs.getString("date_")).append(" ").append("from ").append(rs.getString("startslottime"), 0, 5).append(" to ").append(rs.getString("endslottime"), 0, 5).append("<br>");
                sb.append("<span style='text-align:center;font-weight:bold;'> Doctor's name: </span>").append(rs.getString("name")).append("<br>");
                sb.append("<span style='text-align:center;font-weight:bold;'> Doctor's surname: </span>").append(rs.getString("surname")).append("<br><br>");
            }
            sb.append("</div>");
            conn.close();
            stmt.close();
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sb;
    }
}