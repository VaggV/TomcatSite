package com.Kotz_Telikh.servlets;

import com.Kotz_Telikh.users.Patient;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "patientServlet", value = "/patientServlet")
public class PatientServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        Patient patient = (Patient) session.getAttribute("userObject");

        switch (action) {
            case "showPersonalInfo":
                PrintWriter out = response.getWriter();
                String sb3 = "<span style='font-weight:bold;'>Firstname: </span>" + patient.getName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<span style='font-weight:bold;'>Lastname: </span>" + patient.getSurname() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<span style='font-weight:bold;'>Username: </span>" + patient.getUsername() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
                        "<span style='font-weight:bold;'>Amka: </span>" + patient.getAmka() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                out.println("<p style='color: white; text-align: center;'>" + sb3 + "</p>");
                break;
            case "showHistory":
                StringBuilder sb = patient.appointmentHistory();
                PrintWriter out1 = response.getWriter();
                out1.println(sb);
                break;
            case "bookOrCancel":
                session.setAttribute("calledByServlet", "Yes");
                response.sendRedirect("bookappointment.jsp");
                break;
            case "bookApp":
                StringBuilder sb1 = patient.availableAppointments(request.getParameter("specialty"));
                PrintWriter out2 = response.getWriter();
                out2.println(sb1);
                break;
            case "bookButton":
                String appid = request.getParameter("docSpecialty");
                patient.bookAppointment(Integer.parseInt(appid));
                break;
            case "cancelOrShow":
                StringBuilder sb2 = patient.scheduledAppointments();
                session.setAttribute("scheduledAppointments", sb2);
                session.setAttribute("calledByServlet", "Yes");
                response.sendRedirect("cancelorshowbooked.jsp");
                break;
            case "cancelApp":
                String appointment = request.getParameter("appointmentId");
                String cancelled = patient.cancelAppointment(Integer.parseInt(appointment));
                PrintWriter out4 = response.getWriter();
                out4.println(cancelled);
                break;
        }
    }
}