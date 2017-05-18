<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Employees List</title>
</head>
<body>
<div align="center">
    <h1>Employees List</h1>
    <h3>
        <a href="/Backdoor/newEmployee">New Employee</a>
    </h3>
    <table border="1">
        <th>ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Password</th>
        <th>Avatar ID</th>
        <th>Role ID</th>
        <th>Company ID</th>
        <th>RPT</th>
        <th>CET</th>
        <th>CRT</th>
        <th>Temp Email</th>
        <th>Patronymic</th>
        <th>Workday Type ID</th>

        <c:forEach var="employee" items="${listEmployee}">
            <tr>
                <td>${employee.idemployee}</td>
                <td>${employee.name}</td>
                <td>${employee.surname}</td>
                <td>${employee.email}</td>
                <td>${employee.password}</td>
                <td>${employee.avatarIdimage}</td>
                <td>${employee.roleIdrole}</td>
                <td>${employee.companyIdcompany}</td>
                <td>${employee.resetPasswordToken}</td>
                <td>${employee.changeEmailToken}</td>
                <td>${employee.confirmRegisterToken}</td>
                <td>${employee.tempEmail}</td>
                <td>${employee.patronymic}</td>
                <td>${employee.workdayIdworkdayType}</td>
                <td><a href="/Backdoor/editEmployee?id=${employee.idemployee}">Edit</a>
                    <a href="/Backdoor/deleteEmployee?id=${employee.idemployee}">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
    <h3><a href="/Backdoor/List">Go To List</a><h3/>
</div>
</body>
</html>