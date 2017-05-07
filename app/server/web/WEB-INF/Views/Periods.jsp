<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Periods List</title>
</head>
<body>
<div align="center">
    <h1>Periods List</h1>
    <h3>
        <a href="/newPeriod">New Period</a>
    </h3>
    <table border="1">
        <th>ID</th>
        <th>Start</th>
        <th>Finish</th>
        <th>Employee ID</th>

        <c:forEach var="period" items="${listPeriod}">
            <tr>
                <td>${period.idperiod}</td>
                <td>${period.start}</td>
                <td>${period.finish}</td>
                <td>${period.employeeIdemployee}</td>
                <td><a href="/editPeriod?id=${period.idperiod}">Edit</a>
                    <a href="/deletePeriod?id=${period.idperiod}">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
