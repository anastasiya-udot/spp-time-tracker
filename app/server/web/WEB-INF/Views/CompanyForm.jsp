<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Company</title>
</head>
<body>
<div align="center">
    <h1>New/Edit Image</h1>
    <form:form action="saveCompany" method="post" modelAttribute="company">
        <table>
            <form:hidden path="idcompany"/>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Image ID:</td>
                <td><form:input path="logoIdimage" /></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><form:input path="description" /></td>
            </tr>
            <tr>
                <td>Legal Number:</td>
                <td><form:input path="legalNumber" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>