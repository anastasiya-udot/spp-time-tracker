<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>
<body>
<div align="center">
    <h1>Registration</h1>
    <h3><a href="/Authorization">Authorization</a><h3/>
    <form:form action="Register" method="post" modelAttribute="employee">
        <table>
            <form:hidden path="idemployee"/>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Surname:</td>
                <td><form:input path="surname" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><form:input path="password" /></td>
            </tr>
            <tr>
                <td>Repeat Password:</td>
                <td><input type="text" name="repeat_password"/></td>
            </tr>
            <tr>
                <td>Avatar ID:</td>
                <td>
                    <form:select path="avatarIdimage">
                        <form:options items="${avatars}" itemValue="idimage" itemLabel="url" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>Role ID:</td>
                <td>
                    <form:select path="roleIdrole">
                        <form:options items="${roles}" itemValue="idrole" itemLabel="name" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td>Company ID:</td>
                <td>
                    <form:select path="companyIdcompany">
                        <form:options items="${companies}" itemValue="idcompany" itemLabel="name" />
                    </form:select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Register"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>