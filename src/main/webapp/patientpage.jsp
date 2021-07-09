<%--
  Created by IntelliJ IDEA.
  User: Vaggelis
  Date: 10/6/2021
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Patient</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script type = "text/javascript" src="patientmethods.js"></script>

</head>
<body onload="callServlet('showPersonalInfo')">
  <%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if(session.getAttribute("username") == null) {
      response.sendRedirect("index.jsp");
    }
  %>
  <header>
      <br>
      <div id="personalInfo">
      </div>
  </header>

  <div id="sideMenu" class="sideMenu">
      <form action="Logout">
          <button class="btn">Logout</button>
      </form>
      <br><br>
      <p style="color: white; text-align: center;">
          Today's date is <br> <span style="font-size: 20px; font-weight: bold;">12 June 2021</span>
      </p>
      <div id="btnContainer">
          <button id="searchBtn" class="menuBtns" onclick="callServlet('cancelOrShow')">Booked appointments</button>
          <button id="bookBtn" class="menuBtns" onclick="callServlet('bookOrCancel')">Book appointment</button>
          <button id="showBtn" class="menuBtns" onclick="callServlet('showHistory')">Appointment history</button>
      </div>
  </div>

  <div id="mainSection" style="font-size: 30px;">
      Pick an action from the left side menu
  </div>

</body>
</html>
