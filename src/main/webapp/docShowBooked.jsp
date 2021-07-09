<%--
  Created by IntelliJ IDEA.
  User: Vaggelis
  Date: 15/6/2021
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script type = "text/javascript" src="docmethods.js"></script>
</head>
<body>
<%
    String x = (String) session.getAttribute("calledByServlet");
    if (x == null || x.equals("No")) {
        response.sendRedirect("index.jsp");
        return;
    }

    StringBuilder sb = (StringBuilder) session.getAttribute("showDates");
%>
    <div style="text-align: center;">
        <label for="booked1"></label>
        <select id="booked1">
            <%=sb.toString().split("SPLIT")[1]%>
        </select>
        <button class="buttons" id="cancelAppointment" onclick="cancelAppointment()">Cancel</button>
        <br><br>
        <table id="scheduledTable" style="margin: 0 auto;">
            <%=sb.toString().split("SPLIT")[0]%>
        </table>
    </div>
<%
    session.setAttribute("calledByServlet", "No");
%>
</body>
</html>
