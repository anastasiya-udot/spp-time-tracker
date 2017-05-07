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
        <a href="/newWorkdayType">New Workday Type</a>
    </h3>
    <table border="1">
        <th>ID</th>
        <th>Type Name</th>
        <th>Time</th>

        <c:forEach var="workdayType" items="${listWorkdayType}">
            <tr>
                <td>${workdayType.idworkdayType}</td>
                <td>${workdayType.typename}</td>
                <td>${workdayType.time}</td>
                <td><a href="/editWorkdayType?id=${workdayType.idworkdayType}">Edit</a>
                    <a href="/deleteWorkdayType?id=${workdayType.idworkdayType}">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
