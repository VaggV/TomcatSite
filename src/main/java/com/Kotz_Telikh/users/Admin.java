package com.Kotz_Telikh.users;

import com.Kotz_Telikh.other.HashAndSalt;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

import static com.Kotz_Telikh.other.DbInfo.*;

public class Admin extends Users{
    public Admin(String username, String password, String name, String surname, String salt) {
        super(username, password, name, surname, salt);
    }

    // add doctor to the system
    public String addDoctor(String amka, String username, String password, String name, String surname, String specialty){
        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT user_id FROM admin WHERE username = '" + getUsername() + "'");
            int adminid = 0;
            if (rs.next()) {
                adminid = rs.getInt(1);
            }

            String saltt = HashAndSalt.createSalt();
            byte[] saltBytes = saltt.getBytes();
            byte[] hash = HashAndSalt.hashPass(password, saltBytes);
            String hashedPassword = HashAndSalt.bytesToStringHex(hash);

            stmt.executeUpdate("INSERT INTO doctor (doctor_amka, username, hashedpassword, name, surname, specialty, admin_userid, salt) values ('" + amka + "','" + username + "','" + hashedPassword + "','" + name + "','" + surname + "','" + specialty + "','" + adminid + "','" + saltt + "')");
            stmt.close();
            conn.close();
            return "Doctor inserted to the database!";
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException throwables) {
            return throwables.getMessage();
        }
    }

    // delete doctor from the system
    public String deleteDoctor(String amka){
        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM doctor WHERE doctor_amka = '" + amka + "'");
            if (rs.next()) {
                stmt.executeUpdate("DELETE FROM doctor WHERE doctor_amka='" + amka + "'");
                stmt.close();
                conn.close();
                return "Doctor deleted from the database!";
            } else {
                stmt.close();
                conn.close();
                return "This doctor does not exist.";
            }
        } catch (SQLException throwables) {
            return throwables.getMessage();
        }
    }

    public String addPatient(String amka, String username, String password, String name, String surname){
        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();

            String saltt = HashAndSalt.createSalt();
            byte[] saltBytes = saltt.getBytes();
            byte[] hash = HashAndSalt.hashPass(password, saltBytes);
            String hashedPassword = HashAndSalt.bytesToStringHex(hash);

            stmt.executeUpdate("INSERT INTO patient (patient_amka, username, hashedpassword, name, surname, salt) values ('" + amka + "','" + username + "','" + hashedPassword + "','" + name + "','" + surname + "','" + saltt + "')");
            stmt.close();
            conn.close();
            return "Patient inserted to the database!";
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException throwables) {
            return throwables.getMessage();
        }
    }
}
