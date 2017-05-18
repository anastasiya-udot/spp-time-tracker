<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Image</title>
</head>
<body>
<div align="center">
    <h1>New/Edit Image</h1>
    <form:form action="/Backdoor/saveImage" method="post" modelAttribute="image">
        <table>
            <form:hidden path="idimage"/>
            <tr>
                <td>URL:</td>
                <td><form:input path="url" /></td>
            </tr>
            <tr>
                <td>Public ID:</td>
                <td><form:input path="publicId" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>