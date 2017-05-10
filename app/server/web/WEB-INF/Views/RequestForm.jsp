<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Request</title>
</head>
<body>
<div align="center">
    <h1>New/Edit Image</h1>
    <form:form action="saveRequest" method="post" modelAttribute="request">
        <table>
            <form:hidden path="idrequest"/>
            <tr>
                <td>Source ID:</td>
                <td><form:input path="sourceIdemployee" /></td>
            </tr>
            <tr>
                <td>Destination ID:</td>
                <td><form:input path="destinationIdemployee" /></td>
            </tr>
            <tr>
                <td>Date:</td>
                <td><form:input path="date" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
