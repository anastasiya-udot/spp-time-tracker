<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Projects List</title>
</head>
<body>
<div align="center">
    <h1>Projects List</h1>
    <h3>
        <a href="/Backdoor/newRequest">New Request</a>
    </h3>
    <table border="1">
        <th>ID</th>
        <th>Source ID Employee</th>
        <th>Destination ID Employee</th>
        <th>Date</th>

        <c:forEach var="request" items="${listRequest}">
            <tr>
                <td>${request.idrequest}</td>
                <td>${request.sourceIdemployee}</td>
                <td>${request.destinationIdemployee}</td>
                <td>${request.date}</td>
                <td><a href="/Backdoor/editRequest?id=${request.idrequest}">Edit</a>
                    <a href="/Backdoor/deleteRequest?id=${request.idrequest}">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
    <h3><a href="/Backdoor/List">Go To List</a><h3/>
</div>
</body>
</html>
