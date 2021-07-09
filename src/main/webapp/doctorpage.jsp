<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Doctor</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script type = "text/javascript" src="docmethods.js"></script>
</head>
<body onload="showPersonalInfo1()">
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

    <div class="sideMenu">
        <form action="Logout">
            <button class="btn">Logout</button>
        </form>
        <br><br>
        <p style="color: white; text-align: center;">
            Today's date is <br> <span style="font-size: 20px; font-weight: bold;">12 June 2021</span>
        </p>
        <div id="btnContainer">
            <button class="menuBtns" onclick="openPage()">Insert availability</button>
            <button id="manageBtn" class="menuBtns" onclick="showDates()">Manage upcoming appointments</button>
        </div>
    </div>

    <div id="mainSection" style="font-size: 30px;">
        Pick an action from the left side menu
    </div>
</body>
</html>
