<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <script src="${contextPath}/resources/Chart.bundle.js"></script>
    <script src="${contextPath}/resources/Chart.js"></script>
    <script src="${contextPath}/resources/utils.js"></script>
    <style>
        canvas {
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<security:authorize access="isAuthenticated()">
    <h1>Merhaba <security:authentication property="principal.username"/></h1>
</security:authorize>
<h1>Öğrenci Bilgileri</h1>
Öğrenci Adı: ${student.name}
<h1>Notlar</h1>
<h2>
    Not Listesi
</h2>
<table class="greenTable">
    <thead>
    <th>Ders</th>
    <th>Not</th>
    </thead>
    <c:forEach items="${student.grades}" var="grade">
        <tr>
            <td>${grade.course.name}</td>
            <td>${grade.value}</td>
        </tr>
    </c:forEach>
</table>

<div style="width:75%;">
    <canvas id="canvas"></canvas>
</div>

<script>
    var xlabels = [];
    var yValues = [];

    <c:forEach items="${student.grades}" var="grade" varStatus="loop">
    xlabels.push("${grade.course.name}");
    yValues.push(parseFloat("${grade.value}"));
    </c:forEach>

    var config = {
        type: 'line',
        data: {
            labels: xlabels,
            datasets: [{
                label: 'Notlar',
                backgroundColor: window.chartColors.red,
                borderColor: window.chartColors.red,
                data: yValues,
                fill: false,
            }]
        },
        options: {
            responsive: true,
            title: {
                display: true,
                text: 'Not Değişim Grafiği',
            },
            tooltips: {
                mode: 'index',
                intersect: false,
            },
            hover: {
                mode: 'nearest',
                intersect: true
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Dersler'
                    }
                }],
                yAxes: [{
                    display: true,
                    scaleLabel: {
                        display: true,
                        labelString: 'Not'
                    }
                }]
            }
        }
    };

    window.onload = function () {
        var ctx = document.getElementById('canvas').getContext('2d');
        window.myLine = new Chart(ctx, config);
    };
</script>

<table class="greenTable">
    <c:forEach items="${messages}" var="message">
        <tr>
            <td>${message}</td>
        </tr>
    </c:forEach>
</table>
</body>

</html>
