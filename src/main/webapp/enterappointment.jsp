<%--
  Created by IntelliJ IDEA.
  User: Vaggelis
  Date: 15/6/2021
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #insertBtn{
            border: none;
            background: black;
            color: white;
            padding: 10px;
            border-radius: 10px;
            font-size: 18px;
            font-weight: bold;
        }

        #insertBtn:hover {
            cursor: pointer;
            background: rgba(0,0,0,0.5);
        }
    </style>
    <script type = "text/javascript" src="docmethods.js"></script>
</head>
<body>
<%
    // Οταν το url το γραφει ο χρηστης χειροκινητα δεν ανοιγει η σελιδα
    String x = (String) session.getAttribute("calledByServlet");
    if (x == null || x.equals("No"))
        response.sendRedirect("index.jsp");
%>
    <div id="avContainer" style="text-align: center; margin-top: 30px;">
        <label for="meeting-date" style="font-weight: bold;">Choose a date for your appointment:</label><br><br>
        <input type="date" id="meeting-date" name="meeting-date" value="2021-06-13" min="2021-06-13" max="2030-06-14"><br><br>
        <label for="hours" style="font-weight: bold;">Time</label><br>
        <select id="hours" name="hours">
            <option value="09">09</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
            <option value="13">13</option>
            <option value="14">14</option>
            <option value="15">15</option>
            <option value="16">16</option>
            <option value="17">17</option>
            <option value="18">18</option>
            <option value="19">19</option>
            <option value="20">20</option>
        </select>
        <label for="minutes"></label>
        <select id="minutes">
            <option value="00">00</option>
            <option value="30">30</option>
        </select>
        <br><br>
        <button id="insertBtn" onclick="insertAvailability()">Insert availability</button>
    </div>

<%
    session.setAttribute("calledByServlet", "No");
%>
</body>
</html>
