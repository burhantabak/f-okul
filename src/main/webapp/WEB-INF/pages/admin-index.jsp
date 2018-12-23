<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<h2>Öğrenci Ekle</h2>
<form:form method="POST"
           action="../student/add" modelAttribute="student">
    <table>
        <tr>
            <td><form:label path="name">İsim</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="username">Kullanıcı Adı</form:label></td>
            <td><form:input path="username"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Öğrenciyi Kaydet"/></td>
        </tr>
    </table>
</form:form>
<h2>
    Öğrenci Listesi
</h2>
<table>
<c:forEach items="${students}" var="student">
    <tr><td>${student.name}</td></tr>
</c:forEach>
</table>
</body>

</html>
