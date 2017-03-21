<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="jsp.userstitle"/></title>

    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }

        .text {
            text-align: center;
        }

        table {
            border: 1px solid green;
            margin: auto;
        }
    </style>

</head>


<body>
<div class="text">
    <h2><spring:message code="jsp.userstitle"/></h2>

    <form action="searchUser">
        <spring:message code="jsp.searchlabel"/>
        <input type="text" name="searchName" maxlength='25' title="Search by name:">
        <input class="btn btn-xs" type='submit' value='<spring:message code="jsp.searchbutton"/>'/>
    </form>

    <table>
        <tr>
            <td>ID</td>
            <td><spring:message code="jsp.name"/></td>
            <td><spring:message code="jsp.age"/></td>
            <td><spring:message code="jsp.admin"/></td>
            <td><spring:message code="jsp.date"/></td>
            <td></td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><a href="<c:url value='/edit-${user.name}-user' />"><c:out value="${user.name}"/></a></td>
                <td><c:out value="${user.age}"/></td>
                <td>
                    <c:if test="${user.admin==true}">
                        <spring:message code="jsp.isadmin"/>
                    </c:if>
                </td>
                <td><fmt:formatDate value="${user.createdDate}" type="DATE"/></td>
                <td><a href="<c:url value='/delete-${user.name}-user' />"><spring:message code="jsp.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>

    <br/>
    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span><c:out value="${i.index}"/></span>
            </c:when>
            <c:otherwise>
                <c:url value="/" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'><c:out value="${i.index}"/></a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <br/>
    <br/>
    <a href="<c:url value='/new' />"><spring:message code="jsp.add"/></a>
</div>
</body>
</html>