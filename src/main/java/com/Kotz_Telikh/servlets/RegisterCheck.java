package com.Kotz_Telikh.servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static com.Kotz_Telikh.other.DbInfo.*;

@WebServlet(name = "registerCheck", value = "/registerCheck")
public class RegisterCheck extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        String username = request.getParameter("username");
        String amka = request.getParameter("amka");
        try {
            Connection conn = DriverManager.getConnection(dburl, dbuser, dbpass);
            Statement stmt = conn.createStatement();
            if (amka == null) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM patient WHERE username='" + username + "'");
                if (rs.next()) {
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("Username already exists.");
                } else {
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println(" ");
                }
            } else {
                ResultSet rs = stmt.executeQuery("SELECT * FROM patient WHERE patient_amka='" + amka + "'");
                if (rs.next()) {
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("This AMKA is already registered.");
                } else {
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println(" ");
                }
                rs.close();
            }
            stmt.close();
            conn.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
