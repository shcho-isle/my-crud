<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Registration Form</title>

    <style>

        .error {
            color: #ff0000;
        }
    </style>

</head>

<body>

<h2>Registration Form</h2>

<form:form method="POST" modelAttribute="user">
    <form:input type="hidden" path="id" id="id"/>
    <table>
        <tr>
            <td><label for="name">Name: </label></td>
            <td><form:input path="name" id="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="age">Age: </label></td>
            <td><form:input path="age" id="age"/></td>
            <td><form:errors path="age" cssClass="error"/></td>
        </tr>

        <tr>
            <td><label for="admin">isAdmin: </label></td>
            <td><form:checkbox path="admin" id="admin"/></td>
            <td><form:errors path="admin" cssClass="error"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form:form>
<br/>
Go back to <a href="<c:url value='/list' />">List of All Users</a>
</body>
</html>