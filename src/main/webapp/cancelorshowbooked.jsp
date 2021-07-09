<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Appointments</title>
    <script type = "text/javascript" src="patientmethods.js"></script>
</head>
<body>
<%
    String x = (String) session.getAttribute("calledByServlet");
    if (x == null || x.equals("No")) {
        response.sendRedirect("index.jsp");
        return;
    }
    StringBuilder sb = (StringBuilder) session.getAttribute("scheduledAppointments");
%>
    <div style="text-align: center;">
        <label for="booked1"></label>
        <select id="booked1">
            <%=sb.toString().split("SPLIT")[1]%>
        </select>
        <button class="buttons" id="cancelAppointment" onclick="callServlet('cancelApp')">Cancel</button>
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
