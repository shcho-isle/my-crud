<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="jsp.confirmation"/></title>
</head>
<body>
<spring:message code="jsp.message"/> : ${success}
<br/>
<br/>
<spring:message code="jsp.goback"/> <a href="<c:url value='/list' />"><spring:message code="jsp.listlink"/></a>

</body>

</html>