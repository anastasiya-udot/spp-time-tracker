<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>New/Edit Period</title>
</head>
<body>
<div align="center">
    <h1>New/Edit Period</h1>
    <form:form action="savePeriod" method="post" modelAttribute="period">
        <table>
            <form:hidden path="idperiod"/>
            <tr>
                <td>Start:</td>
                <td><form:input path="start" /></td>
            </tr>
            <tr>
                <td>Finish:</td>
                <td><form:input path="finish" /></td>
            </tr>
            <tr>
                <td>Employee ID:</td>
                <td><form:input path="employeeIdemployee" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
