<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>

<body>
<security:authorize access="isAuthenticated()">
    authenticated as <security:authentication property="principal.username" />
</security:authorize>

<h1>merhaba  ${person.name}</h1>

    <c:forEach items="${person.messages}" var="message">
        ${message.subject} <br/>
        ${message.content}
    </c:forEach>
</body>

</html>
