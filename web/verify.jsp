<%-- 
    Document   : verify
    Created on : Oct 28, 2020, 7:58:12 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Verify</h1>
        <h2>Input your code</h2>
        <form action="AddToDatabaseController" method="POST">
<!--        <form action="AddToDatabase_Remark1" method="POST">-->
            <input type="hidden" name="txtCode" value="${requestScope.CODE}" />
            <input type="hidden" name="txtTotal" value="${requestScope.TOTAL}" />
            <input type="hidden" name="txtCheckIn" value="${requestScope.txtCheckIn}" />
            <input type="hidden" name="txtCheckOut" value="${requestScope.txtCheckOut}" />
            <input type="number" name="txtCheckCode" value="" required="true" min="000000" max="999999"/>
            <input type="submit" value="Check"/>
        </form><br/>
    </body>
</html>
