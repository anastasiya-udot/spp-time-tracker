<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Company</title>
</head>
<body>
<div align="center">
    <h1>Authorisation</h1>
    <h3><a href="/Registration">Registration</a><h3/>
    <form:form action="Authorize" method="post">
        <table>
            <tr>
                <td>Email:</td>
                <td><input type="text" name="email"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="text" name="password"/></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Authorize"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
