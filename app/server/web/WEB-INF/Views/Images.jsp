<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Images List</title>
</head>
<body>
<div align="center">
    <h1>Images List</h1>
    <h3>
        <a href="newImage">New Image</a>
    </h3>
    <table border="1">

        <th>ID</th>
        <th>URL</th>
        <th>Public ID</th>

        <c:forEach var="image" items="${listImage}">
            <tr>
                <td>${image.idimage}</td>
                <td>${image.url}</td>
                <td>${image.publicId}</td>
                <td><a href="editImage?id=${image.idimage}">Edit</a>
                    <a href="deleteImage?id=${image.idimage}">Delete</a></td>

            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>