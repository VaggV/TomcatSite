<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script type = "text/javascript" src="patientmethods.js"></script>
</head>
<body>
    <%
        String x = (String) session.getAttribute("calledByServlet");
        if (x == null || x.equals("No"))
            response.sendRedirect("index.jsp");
    %>

    <p style="text-align: center;">Select a specialty from the combo box then click search to show available doctors with that specialty.<br>Then choose the appointment id you want to book from the other combo box and press the book button.</p>
    <div style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
        <label for="specialties"></label>
        <select id="specialties">
            <option value="Pathologist">Pathologist</option>
            <option value="Orthopedist">Orthopedist</option>
            <option value="Ophthalmologist">Ophthalmologist</option>
        </select>
        <button id="searchAppointment" class="buttons" onclick="callServlet1()">Search</button>
        <label for="appointmentlist"></label>
        <div id="bookDiv" style="display: none;">
            <select id="appointmentlist"></select>
            <button class="buttons" onclick="callServlet('bookButton')">Book</button>
        </div>
    </div>
    <div id="availableDates1">
        <table id="availableDates2" style="margin: 0 auto;"></table>
    </div>

    <%
        session.setAttribute("calledByServlet", "No");
    %>
</body>
</html>
