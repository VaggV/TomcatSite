package com.Kotz_Telikh.servlets;

import com.Kotz_Telikh.other.HashAndSalt;
import com.Kotz_Telikh.users.Patient;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        String p_username = request.getParameter("username");
        String p_password = request.getParameter("password");
        String p_name = request.getParameter("name");
        String p_surname = request.getParameter("surname");
        String p_amka = request.getParameter("amka");
        String salt = HashAndSalt.createSalt();
        byte[] saltBytes = salt.getBytes();
        try {
            byte[] hash = HashAndSalt.hashPass(p_password, saltBytes);
            String hashedPassword = HashAndSalt.bytesToStringHex(hash);
            Patient patient = new Patient(p_username, hashedPassword, p_name, p_surname, p_amka, salt);
            patient.register();
            response.sendRedirect("login.jsp");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }
}
