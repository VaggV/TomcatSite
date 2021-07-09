package com.Kotz_Telikh.servlets;

import com.Kotz_Telikh.users.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static com.Kotz_Telikh.other.DbInfo.*;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        Admin admin = (Admin) session.getAttribute("userObject");

        switch (action) {
            case "showPersonalInfo":
                PrintWriter out = response.getWriter();
                String sb3 = "<span style='font-weight:bold;'>Firstname: </span>" + admin.getName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<span style='font-weight:bold;'>Lastname: </span>" + admin.getSurname() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<span style='font-weight:bold;'>Username: </span>" + admin.getUsername() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                out.println("<p style='color: white; text-align: center;'>" + sb3 + "</p>");
                break;
            case "insertDoctor":
                String amka = request.getParameter("amka");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String specialty = request.getParameter("specialty");
                String result = admin.addDoctor(amka, username, password, name, surname, specialty);
                PrintWriter out1 = response.getWriter();
                out1.println(result);
                break;
            case "deleteDoctor":
                String amka1 = request.getParameter("amka");
                String result1 = admin.deleteDoctor(amka1);
                PrintWriter out2 = response.getWriter();
                out2.println(result1);
                break;
            case "insertPatient":
                String p_amka = request.getParameter("amka");
                String p_username = request.getParameter("username");
                String p_password = request.getParameter("password");
                String p_name = request.getParameter("name");
                String p_surname = request.getParameter("surname");
                String resultt = admin.addPatient(p_amka, p_username, p_password, p_name, p_surname);
                PrintWriter out3 = response.getWriter();
                out3.println(resultt);
                break;
        }
    }
}
