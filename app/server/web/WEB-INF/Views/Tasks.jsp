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
    <h1>Tasks List</h1>
    <h3>
        <a href="/Backdoor/newTask">New Task</a>
    </h3>
    <table border="1">
        <th>ID</th>
        <th>Code</th>
        <th>Description</th>
        <th>Project ID</th>

        <c:forEach var="task" items="${listTask}">
            <tr>
                <td>${task.idtask}</td>
                <td>${task.code}</td>
                <td>${task.description}</td>
                <td>${task.projectIdproject}</td>
                <td><a href="/Backdoor/editTask?id=${task.idtask}">Edit</a>
                    <a href="/Backdoor/deleteTask?id=${task.idtask}">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
    <h3><a href="/Backdoor/List">Go To List</a><h3/>
</div>
</body>
</html>
