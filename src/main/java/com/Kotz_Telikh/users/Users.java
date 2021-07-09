package com.Kotz_Telikh.users;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.Kotz_Telikh.other.DbInfo.*;
import static com.Kotz_Telikh.other.DbInfo.dateNow;

public abstract class Users {
    private static int usersCounter = 0;
    private String username;
    String password;
    private String name;
    private String surname;
    private String salt;

    public Users(String username, String password, String name, String surname, String salt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.salt = salt;
        usersCounter += 1;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
        usersCounter += 1;
    }

    public void login(){

    }

    // password getter and setter
    public String getPassword(){
        return password;
    }

    // name getter and setter
    public String getName(){
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    // surname getter and setter
    public String getSurname(){
        return surname;
    }

    public void setSurname(String newSurname){
        this.surname = newSurname;
    }

    // username getter and setter
    public String getUsername(){
        return username;
    }

    public void setUsername(String newUsername){
        this.username = newUsername;
    }

    // usersCounter getter
    public static int getUsersCounter(){
        return usersCounter;
    }

    // salt getter
    public String getSalt() {
        return salt;
    }

    public String cancelAppointment(int appid){
        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT date_ FROM appointment WHERE appointment_id = '" + appid + "'");
            String appDate = "";
            if (rs.next()){
                appDate = rs.getString(1);
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dApp = sdf.parse(appDate);
            Date dNow = sdf.parse(dateNow);

            long diffInMillies = Math.abs(dApp.getTime() - dNow.getTime());
            int diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            System.out.println("Difference of 2 dates is : " + diff);

            if (diff > 3) {
                stmt.executeUpdate("UPDATE appointment SET isavailable = true, patient_amka = '' WHERE appointment_id = '" + appid + "'");
                conn.close();
                stmt.close();
            } else {
                conn.close();
                stmt.close();
                return "false";
            }

        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
        }

        return "true";
    }
}
