<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>

<body>
<security:authorize access="isAuthenticated()">
    <h1>Merhaba <security:authentication property="principal.username"/></h1>
</security:authorize>
<h1>Öğrenci Bilgileri</h1>
Öğrenci Adı: ${student.name}
<h1>Notlar</h1>
<h2>Not Ekle</h2>
<form:form method="POST"
           action="${contextPath}/grade/add" modelAttribute="grade">
    <table>
        <tr>
            <td><form:hidden path="student"/></td>
            <td><form:label path="course">Ders</form:label></td>
            <td><form:select path="course">
                <form:options itemValue="id" itemLabel="name" items="${courses}"/>
            </form:select></td>
        </tr>
        <tr>
            <td><form:label path="grade.value">Not</form:label></td>
            <td><form:input path="grade.value"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Notu Kaydet"/></td>
        </tr>
    </table>
</form:form>
<h2>
    Not Listesi
</h2>
<table>
    <c:forEach items="${student.grades}" var="grade">
        <tr>
            <td>${grade.course.name}</td>
            <td>${grade.value}</td>
        </tr>
    </c:forEach>
</table>

</body>

</html>
