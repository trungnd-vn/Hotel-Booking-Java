<%-- 
    Document   : register
    Created on : Oct 27, 2020, 9:08:10 PM
    Author     : HOME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h1>Sign Up</h1>
        <form action="SignUpController" method="POST">
            <input type="text" name="txtUserID" value="" placeholder="Username"/><br/><br/>
            <font color="red">${requestScope.INVALID.emailErr}</font><br/>
            <input type="password" name="txtPassword" value="" placeholder="Password"/><br/><br/>            
            <font color="red">${requestScope.INVALID.passErr}</font><br/>
            <input type="password" name="txtConfirm" value="" placeholder="ConFirm"/><br/><br/>
            <font color="red">${requestScope.INVALID.confirmErr}</font><br/>
            <input type="text" name="txtName" value="" placeholder="Name"/><br/><br/>            
            <font color="red">${requestScope.INVALID.nameErr}</font><br/>
            <input type="text" name="txtPhone" value="" placeholder="Phone"/><br/><br/>
            <font color="red">${requestScope.INVALID.phoneError}</font><br/>
            <input type="text" name="txtAddress" value="" placeholder="Address"/><br/><br/>            
            <input type="submit" value="SignUp" /><br/>
        </form>
    </body>
</html>
