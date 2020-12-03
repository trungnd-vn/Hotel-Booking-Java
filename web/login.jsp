<%-- 
    Document   : login
    Created on : Oct 27, 2020, 9:07:03 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="css/style_login.css"/>
    </head>
    <body>
<!--        <h1>WELCOME TO OUR SITE</h1>-->
        <!--        <form action="LoginController" method="POST">
                    <input type="text" name="txtUsername" value="" placeholder="Username"/><br/><br/>
                    <input type="password" name="txtPassword" value="" placeholder="Password"/><br/><br/>
                    <input type="hidden" name="txtSearch" value="">
        ${requestScope.INVALID.idError}
        ${requestScope.INVALID.passwordError}
        <input type="submit" value="Login" /><br/><br/>
    </form>-->
<!--        <form action="register.jsp" method="POST">
            <input type="submit" value="Register" />
        </form>-->

        <div class="cont">
            <div class="form sign-in">
                <form action="LoginController" method="POST">              
                    <h2>Welcome back,</h2>
                    <label>
                        <span>Username</span>
                        <input type="text" name="txtUsername" value=""/>
                        <font color="red">${requestScope.INVALID.idError}</font>
                    </label>
                    <label>
                        <span>Password</span>
                        <input type="password" name="txtPassword"/>
                        <font color="red">${requestScope.INVALID.passwordError}</font>
                    </label>
                    <input type="hidden" name="cbxHotelName" value="null"/>
                    <center><font color="red">${requestScope.ERROR}</font></center>
                    <p class="forgot-pass">Forgot password?</p>                  
                    <input type="submit" class="submit" name="action" value="Login"/>               
                </form>
                <br/>                
            </div>
            <div class="sub-cont">
                <font color="red"><h3>${requestScope.DUPLICATE}</h3></font>
                <form action="SignUpController" method="POST">
                    <div class="img">
                        <div class="img__text m--up">
                            <h2>New here?</h2>
                            <p>Sign up and discover great amount of new opportunities!</p>
                        </div>
                        <div class="img__text m--in">
                            <h2>One of us?</h2>
                            <p>If you already has an account, just sign in. We've missed you!</p>
                        </div>
                        <div class="img__btn">
                            <span class="m--up">Sign Up</span>
                            <span class="m--in">Sign In</span>
                        </div>
                    </div>
                    <div class="form sign-up">
                        <h2>Time to feel like home,</h2>
                        <label>
                            <span>Username</span>
                            <input type="text" name="txtUserID" value=""/>
                            <font color="red">${requestScope.INVALID.emailErr}</font>
                        </label>
                        <label>
                            <span>Name</span>
                            <input type="text" name="txtName" value=""/>
                            <font color="red">${requestScope.INVALID.nameErr}</font>
                        </label>
                        <label>
                            <span>Password</span>
                            <input type="password" name="txtPassword"/>
                            <font color="red">${requestScope.INVALID.passErr}</font>
                        </label>
                        <label>
                            <span>Confirm</span>
                            <input type="password" name="txtConfirm"/>
                            <font color="red">${requestScope.INVALID.confirmErr}</font>
                        </label>
                        <label>
                            <span>Phone</span>
                            <input type="number" name="txtPhone"/>
                            <font color="red">${requestScope.INVALID.phoneError}</font>
                        </label>
                        <label>
                            <span>Address</span>
                            <input type="text" name="txtAddress" placeholder="---Optional---"/>
                        </label>                                       
                        <input type="submit" class="submit" value="SignUp" name="action"/>
                        <button type="button" class="fb-btn">Join with <span>Facebook</span></button>
                    </div>
                </form>
            </div>
        </div>

        <script type="text/javascript" src="js/login.js"></script>
    </body>
</html>
