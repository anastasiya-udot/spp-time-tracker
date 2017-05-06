<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Roles List</title>
</head>
<body>
<div align="center">
    <h1>Roles List</h1>
    <h3>
        <a href="/newRole">New Role</a>
    </h3>
    <table border="1">
        <th>ID</th>
        <th>Name</th>
        <th>Code</th>

        <c:forEach var="role" items="${listRole}">
            <tr>
                <td>${role.idrole}</td>
                <td>${role.name}</td>
                <td>${role.code}</td>
                <td><a href="editRole?id=${role.idrole}">Edit</a>
                    <a href="deleteRole?id=${role.idrole}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>