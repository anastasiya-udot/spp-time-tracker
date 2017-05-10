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
        <a href="/newProject">New Project</a>
    </h3>
    <table border="1">
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Company ID</th>

        <c:forEach var="project" items="${listProject}">
            <tr>
                <td>${project.idproject}</td>
                <td>${project.name}</td>
                <td>${project.description}</td>
                <td>${project.companyIdcompany}</td>
                <td><a href="editProject?id=${project.idproject}">Edit</a>
                <a href="deleteProject?id=${project.idproject}">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>