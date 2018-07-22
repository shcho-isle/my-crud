<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="jsp.reg_tab"/></title>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
</head>
<body>
<h2><spring:message code="jsp.reg_title"/></h2>
<form:form method="POST" modelAttribute="user">
    <form:input type="hidden" path="id" id="id"/>
    <form:input type="hidden" path="createdDate" id="createdDate"/>
    <table>
        <tr>
            <td><label for="name"><spring:message code="jsp.name"/>: </label></td>
            <td><form:input path="name" id="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="age"><spring:message code="jsp.age"/>: </label></td>
            <td><form:input path="age" id="age"/></td>
            <td><form:errors path="age" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="admin"><spring:message code="jsp.admin"/>: </label></td>
            <td><form:checkbox path="admin" id="admin"/></td>
            <td><form:errors path="admin" cssClass="error"/></td>
        </tr>
        <tr>
            <td><input type="submit" value='<spring:message code="jsp.save_button"/>'/></td>
        </tr>
    </table>
</form:form>
<br/>
<spring:message code="jsp.go_back"/> <a href="<c:url value='/list' />"><spring:message code="jsp.list_link"/></a>
</body>
</html>