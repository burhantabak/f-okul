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
<h1>Öğrenciler</h1>
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
    <c:forEach items="${students}" var="std">
        <tr>
            <td>${std.name}</td><td><a href = "../grade/list/${std.id}">Not Ekle</a></td>
        </tr>
    </c:forEach>
</table>
<h1>Dersler</h1>
<h2>Ders Ekle</h2>
<form:form method="POST"
           action="../course/add" modelAttribute="course">
    <table>
        <tr>
            <td><form:label path="name">İsim</form:label></td>
            <td><form:input path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="coefficient">Katsayı</form:label></td>
            <td><form:input path="coefficient"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Dersi Kaydet"/></td>
        </tr>
    </table>
</form:form>
<h2>
    Ders Listesi
</h2>
<table>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.name}</td>
        </tr>
    </c:forEach>
</table>
</body>

</html>
