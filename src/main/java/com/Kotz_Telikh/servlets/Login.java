package com.Kotz_Telikh.servlets;

import com.Kotz_Telikh.other.HashAndSalt;
import com.Kotz_Telikh.users.Admin;
import com.Kotz_Telikh.users.Doctor;
import com.Kotz_Telikh.users.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;

import static com.Kotz_Telikh.other.DbInfo.*;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        String uname = request.getParameter("username");
        String pass_ = request.getParameter("password");
        String usertype = request.getParameter("user");

        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            switch (usertype) {
                case "patient": {
                    ResultSet rs1 = stmt.executeQuery("SELECT salt FROM patient WHERE username ='" + uname + "'");
                    String hashedPassword = "";
                    if (rs1.next()) {
                        String salt = rs1.getString(1);
                        byte[] saltBytes = salt.getBytes();
                        byte[] hash = HashAndSalt.hashPass(pass_, saltBytes);
                        hashedPassword = HashAndSalt.bytesToStringHex(hash);
                    }
                    ResultSet rs = stmt.executeQuery("SELECT * FROM patient WHERE username = '" + uname + "' AND hashedpassword = '" + hashedPassword + "'");

                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        // set session attributes after the database connection
                        // is successfull and the user is found
                        Patient patient = new Patient(uname, pass_, rs.getString("name"), rs.getString("surname"), rs.getString("patient_amka"), rs.getString("salt"));
                        session.setAttribute("username", uname);
                        session.setAttribute("usertype", usertype);
                        session.setAttribute("userObject", patient);
                        response.sendRedirect("patientpage.jsp");
                    } else {
                        response.sendRedirect("login.jsp");
                    }
                    rs.close();
                    break;
                }
                case "doctor": {
                    ResultSet rs1 = stmt.executeQuery("SELECT salt FROM doctor WHERE username ='" + uname + "'");
                    String hashedPassword = "";
                    if (rs1.next()) {
                        String salt = rs1.getString(1);
                        byte[] saltBytes = salt.getBytes();
                        byte[] hash = HashAndSalt.hashPass(pass_, saltBytes);
                        hashedPassword = HashAndSalt.bytesToStringHex(hash);
                    }
                    ResultSet rs = stmt.executeQuery("SELECT * FROM doctor WHERE username = '" + uname + "' AND hashedpassword = '" + hashedPassword + "'");
                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        Doctor doctor = new Doctor(rs.getString("doctor_amka"), uname, pass_, rs.getString("name"), rs.getString("surname"), rs.getString("specialty"), rs.getString("salt"));
                        session.setAttribute("username", uname);
                        session.setAttribute("usertype", usertype);
                        session.setAttribute("userObject", doctor);
                        response.sendRedirect("doctorpage.jsp");
                    } else {
                        response.sendRedirect("login.jsp");
                    }
                    rs.close();
                    break;
                }
                case "admin": {
                    ResultSet rs1 = stmt.executeQuery("SELECT salt FROM admin WHERE username ='" + uname + "'");
                    String hashedPassword = "";
                    if (rs1.next()) {
                        String salt = rs1.getString(1);
                        byte[] saltBytes = salt.getBytes();
                        byte[] hash = HashAndSalt.hashPass(pass_, saltBytes);
                        hashedPassword = HashAndSalt.bytesToStringHex(hash);
                    }
                    ResultSet rs = stmt.executeQuery("SELECT * FROM admin WHERE username = '" + uname + "' AND hashedpassword = '" + hashedPassword + "'");
                    if (rs.next()) {
                        HttpSession session = request.getSession();
                        Admin admin = new Admin(uname, pass_, rs.getString("name"), rs.getString("surname"), rs.getString("salt"));
                        session.setAttribute("username", uname);
                        session.setAttribute("usertype", usertype);
                        session.setAttribute("userObject", admin);
                        response.sendRedirect("adminpage.jsp");
                    } else {
                        response.sendRedirect("login.jsp");
                    }
                    rs.close();
                    break;
                }
            }
            conn.close();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            response.sendRedirect("error.jsp");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }
}
