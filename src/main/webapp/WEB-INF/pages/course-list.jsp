<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>

<body>
<security:authorize access="isAuthenticated()">
    <h1>Merhaba <security:authentication property="principal.username"/></h1>
</security:authorize>

<c:forEach items="${courses}" var="course">
    ${course.name} <br/>
</c:forEach>
</body>

</html>
