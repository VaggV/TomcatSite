<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home Page</title>
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache, no-store");
    String usertype = (String) session.getAttribute("usertype");
    if (usertype != null) {
        if (usertype.equals("patient"))
            response.sendRedirect("patientpage.jsp");
        else if (usertype.equals("doctor"))
            response.sendRedirect("doctorpage.jsp");
        else if (usertype.equals("admin"))
            response.sendRedirect("adminpage.jsp");
    }
%>
<div style="font-size: 80px; font-weight: bold; margin:270px auto; text-align: center;">
    <a href="login.jsp" style="text-decoration: none; font-family: 'Century Gothic',serif; ">LOGIN</a> <br><br>
    <a href="register.jsp" style="text-decoration: none; font-family: 'Century Gothic',serif; ">REGISTER</a>
</div>
</body>
</html>
