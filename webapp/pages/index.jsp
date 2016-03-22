<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/index"/>">Главная</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="<c:url value="/profile"/>">Профиль</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('CREATE NEW USER')">
                    <li>
                        <a href="<c:url value="/usermanagement"/>">Управление пользователями</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('CREATE NEW ROLE')">
                <li>
                    <a href="<c:url value="/rolemanagement"/>">Управление ролями</a>
                </li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="hasAuthority('CREATE NEW TASK')">
                    <li>
                        <a class="btn btn-primary" href="<c:url value="/newtask"/>">Создать Задание</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="<c:url value="/logout"/>">Выйти</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <li>
                        <a class="btn btn-primary" href="<c:url value="/login"/>">Логин</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<c:forEach var="task" items="${tasks}">
    <div class="col-md-offset-1 col-md-10">
        <div class="col-md-8">
            <a href="/task/${task.id}" class="h2 btn-link">${task.theme}</a>
            <div class="col-md-12 text-left">
                <p>${task.description}</p>
            </div>
        </div>
        <div class="col-md-4 text-right">
            <c:if test="${task.price > 0}">
                <h2>$${task.price}</h2>
            </c:if>
            <c:if test="${task.price == 0}">
                <h4>По договоренности</h4>
            </c:if>
        </div>
        <div class="col-md-8 text-left">
            <span><b>Автор:</b> ${task.customer.login}</span>
            <span><b>Создан:</b> ${task.createDate}</span>
            <span><b>Просмотров:</b> ${task.visitCount}</span>
        </div>
        <div class="col-md-4 text-right">
            <span><b>Статус:</b><b id="h3"> ${task.taskStatus.displayName}</b></span>
        </div>
    </div>
    <div class="col-md-offset-1 col-md-10 line-separator"></div>
</c:forEach>
<div class="col-md-12 footer-expand"></div>
</body>
</html>
