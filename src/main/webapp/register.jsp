<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->
    <script src="https://code.jquery.com/jquery-2.2.1.min.js"></script>

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
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">

            <form method="post" class="login100-form validate-form" action="Register">
                <span class="login100-form-title p-b-26">Insert your info to register</span>
                <p id="result" style="color: red; margin:0 auto; text-align: center;"></p>
                <br><br>
                <div class="wrap-input100 validate-input" data-validate="Enter username">
                    <input id="username" class="input100" type="text" name="username" required>
                    <span class="focus-input100" data-placeholder="Username"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
                    <input id="password" class="input100" type="password" name="password" required>
                    <span class="focus-input100" data-placeholder="Password"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter name">
                    <input id="name" class="input100" type="text" name="name" required>
                    <span class="focus-input100" data-placeholder="Name"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter surname">
                    <input id="surname" class="input100" type="text" name="surname" required>
                    <span class="focus-input100" data-placeholder="Surname"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="Enter amka">
                    <input id="amka" class="input100" type="text" name="amka" minlength="11" maxlength="11" required>
                    <span class="focus-input100" data-placeholder="AMKA"></span>
                </div>

                <p id="result2" style="color: red; margin: 0 auto; text-align: center;"></p>

                <div class="container-login100-form-btn" style="margin-top: 50px;">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn">
                            Register
                        </button>
                    </div>
                </div>
                <div class="text-center p-t-20">
                    <span class="txt1">
                        Already registered?
                    </span>
                    <a class="txt2" href="login.jsp">
                        Login
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>



<script>
    $(document).ready(function(){
        $('#username').change(function(){
           let usern = $('#username').val();
           $.ajax({
               type:'POST',
               data: {username:usern},
               url: 'registerCheck',
               success: function (result){
                   $('#result').html(result);
               }
           });
        });
        $('#amka').change(function (){
           let amka = $('#amka').val();
           $.ajax({
              type:'POST',
              data: {amka:amka},
              url: 'registerCheck',
              success: function (result){
                  $('#result2').html(result);
              }
           });
        });
    });
</script>


<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="js/main.js"></script>

</body>
</html>
