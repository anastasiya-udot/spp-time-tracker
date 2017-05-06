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
    <h1>New/Edit Image</h1>
    <form:form action="saveEmployee" method="post" modelAttribute="employee">
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
                <td>Avatar ID:</td>
                <td><form:input path="avatarIdimage" /></td>
            </tr>
            <tr>
                <td>Role ID:</td>
                <td><form:input path="roleIdrole" /></td>
            </tr>
            <tr>
                <td>Company ID:</td>
                <td><form:input path="companyIdcompany" /></td>
            </tr>
            <tr>
                <td>Reset Password Token:</td>
                <td><form:input path="resetPasswordToken" /></td>
            </tr>
            <tr>
                <td>Change Email Token:</td>
                <td><form:input path="changeEmailToken" /></td>
            </tr>
            <tr>
                <td>Confirm Register Token:</td>
                <td><form:input path="confirmRegisterToken" /></td>
            </tr>
            <tr>
                <td>Temp Email:</td>
                <td><form:input path="tempEmail" /></td>
            </tr>
            <tr>
                <td>Patronymic:</td>
                <td><form:input path="patronymic" /></td>
            </tr>
            <tr>
                <td>Workday Type ID:</td>
                <td><form:input path="workdayIdworkdayType" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>