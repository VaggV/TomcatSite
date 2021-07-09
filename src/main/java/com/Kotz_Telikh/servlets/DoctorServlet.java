package com.Kotz_Telikh.servlets;

import com.Kotz_Telikh.users.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DoctorServlet", value = "/DoctorServlet")
public class DoctorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        Doctor doctor = (Doctor) session.getAttribute("userObject");
        switch (action) {
            case "openEnterAppointment":
                session.setAttribute("calledByServlet", "Yes");
                response.sendRedirect("enterappointment.jsp");
                break;
            case "insertAvail":
                String date = request.getParameter("date");
                String time = request.getParameter("time");
                String entered = doctor.insertAvailability(date, time);
                PrintWriter outt = response.getWriter();
                outt.println(entered);
                break;
            case "showDates":
                StringBuilder sb = doctor.showDates();
                session.setAttribute("showDates", sb);
                session.setAttribute("calledByServlet", "Yes");
                response.sendRedirect("docShowBooked.jsp");
                break;
            case "cancelByDoc":
                int appointment = Integer.parseInt(request.getParameter("appid"));
                String cancelled = doctor.cancelAppointment(appointment);
                PrintWriter out = response.getWriter();
                out.println(cancelled);
                break;
            case "showPersonalInfo":
                PrintWriter out1 = response.getWriter();
                String sb1 = "<span style='font-weight:bold;'>Firstname: </span>" + doctor.getName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<span style='font-weight:bold;'>Lastname: </span>" + doctor.getSurname() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<span style='font-weight:bold;'>Username: </span>" + doctor.getUsername() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
                        "<span style='font-weight:bold;'>Amka: </span>" + doctor.getAmka() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                out1.println("<p style='color: white; text-align: center;'>" + sb1 + "</p>");
                break;
        }

    }
}