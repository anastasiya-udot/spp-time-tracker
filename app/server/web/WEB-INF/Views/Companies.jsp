<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Companies List</title>
</head>
<body>
<div align="center">
    <h1>Company List</h1>
    <h3>
        <a href="/Backdoor/newCompany">New Company</a>
    </h3>
    <table border="1">

        <th>ID</th>
        <th>Name</th>
        <th>Logo ID</th>
        <th>Description</th>
        <th>Legal Number</th>

        <c:forEach var="company" items="${listCompany}">
            <tr>
                <td>${company.idcompany}</td>
                <td>${company.name}</td>
                <td>${company.logoIdimage}</td>
                <td>${company.description}</td>
                <td>${company.legalNumber}</td>
                <td><a href="/Backdoor/editCompany?id=${company.idcompany}">Edit</a>
                    <a href="/Backdoor/deleteCompany?id=${company.idcompany}">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
    <h3><a href="/Backdoor/List">Go To List</a><h3/>
</div>
</body>
</html>