<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script type = "text/javascript" src="adminmethods.js"></script>
    <script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>
    <style>
        label {
            font-weight: bold;
        }
    </style>
</head>
<body onload="showPersonalInfo()">
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

    <%
        String x = "dsadjisajdsa";
        System.out.println("dsadsadas");
    %>

    <div class="sideMenu">
        <form action="Logout">
            <button class="btn">Logout</button>
        </form>
        <br><br>
        <p style="color: white; text-align: center;">
            Today's date is <br> <span style="font-size: 20px; font-weight: bold;">12 June 2021</span>
        </p>
    </div>

    <div id="mainSection" style="font-size: 15px;">
        <div id="formContainer" style="width: 400px; height: 300px; margin: 20px auto; text-align: center; padding-top: 40px;">

            <form action="" id="insertDocForm" onsubmit="insertDoctor()">

                <table id="insertDoc">
                    <tr>
                        <td><label for="amka">AMKA:</label></td>
                        <td><input id="amka" type="text" minlength="11" maxlength="11" required></td>
                    </tr>
                    <tr>
                        <td><label for="username">Username:</label></td>
                        <td><input id="username" type="text" required></td>
                    </tr>
                    <tr>
                        <td><label for="password">Password:</label></td>
                        <td><input id="password" type="password" required></td>
                    </tr>
                    <tr>
                        <td><label for="name">Name:</label></td>
                        <td><input id="name" type="text" required></td>
                    </tr>
                    <tr>
                        <td><label for="surname">Surname:</label></td>
                        <td><input id="surname" type="text" required></td>
                    </tr>
                    <tr>
                        <td><label for="specialty">Specialty:</label></td>
                        <td>
                            <select id="specialty">
                                <option value="Ophthalmologist">Ophthalmologist</option>
                                <option value="Pathologist">Pathologist</option>
                                <option value="Orthopedist">Orthopedist</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <input type="submit" class="btn" value="Insert doctor">
                        </td>
                    </tr>
                </table>
            </form>

            <form style="margin-top: 70px;" action="" id="deleteDocForm" onsubmit="deleteDoctor()">
                <table id="deleteDoc">
                    <tr>
                        <td colspan="2" style="text-align: center; font-weight: bold;">Delete doctor</td>
                    </tr>
                    <tr>
                        <td><label for="amka1">AMKA:</label></td>
                        <td><input id="amka1" type="text" required minlength="11" maxlength="11"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <input type="submit" class="btn" value="Delete Doctor">
                        </td>
                    </tr>
                </table>
            </form>

            <form style="margin-top: 70px;" action="" id="insertPatientForm" onsubmit="insertPatient()">
                <table id="insertPatient">
                    <tr>
                        <td><label for="p_amka">AMKA:</label></td>
                        <td><input id="p_amka" type="text" required maxlength="11" minlength="11"></td>
                    </tr>
                    <tr>
                        <td><label for="p_username">Username:</label></td>
                        <td><input id="p_username" type="text" required></td>
                    </tr>
                    <tr>
                        <td><label for="p_password">Password:</label></td>
                        <td><input id="p_password" type="password" required></td>
                    </tr>
                    <tr>
                        <td><label for="p_name">Name:</label></td>
                        <td><input id="p_name" type="text" required></td>
                    </tr>
                    <tr>
                        <td><label for="p_surname">Surname:</label></td>
                        <td><input id="p_surname" type="text" required></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <input type="submit" class="btn" value="Insert patient">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <script>
        // jquery για να μην κανουν reload οι φορμες στο submit
        $(document).ready(function () {
            $(document).on('submit', '#insertDocForm', function(){
                return false;
            });
            $(document).on('submit', '#deleteDocForm', function(){
                return false;
            });
            $(document).on('submit', '#insertPatientForm', function(){
                return false;
            });
        });
    </script>
</body>
</html>
