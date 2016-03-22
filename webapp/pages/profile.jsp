<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Profile</title>
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
                <li>
                    <a href="<c:url value="/index"/>">Задания</a>
                </li>
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
            </ul>
        </div>
    </div>
</nav>
<div class="col-md-offset-1 col-md-offset-10">
    <h2 class="profile">${userProfile.login} (${userProfile.firstName} ${userProfile.login})</h2>
    <h3 class="profile">${userProfile.email}</h3>
    <h2></h2>
    <h2></h2>
    <c:if test="${isCurrentUser}">
        <div class="col-md-12">
            <h3>Мои задания</h3>
            <c:forEach var="task" items="${tasks}">
                <div class="col-md-offset-1 col-md-10">
                    <div class="col-md-8">
                        <a href="/task/${task.id}" class="h2 btn-link">${task.theme}</a>
                        <span>(${task.taskRequests.size()} откликов)</span>
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
                        <span><b>Создан:</b> ${task.createDate}</span>
                        <span><b>Просмотров:</b> ${task.visitCount}</span>
                    </div>
                    <div class="col-md-4 text-right">
                        <span><b>Статус:</b> ${task.taskStatus.displayName}</span>
                    </div>
                </div>
                <div class="col-md-offset-1 col-md-10 line-separator"></div>
            </c:forEach>
        </div>
        <div class="col-md-12">
            <h3>Мои отклики</h3>
            <c:forEach var="taskRequest" items="${taskRequests}">
                <div class="col-md-offset-1 col-md-10">
                    <div class="col-md-8">
                        <a href="/task/${taskRequest.task.id}" class="h2 btn-link">${taskRequest.task.theme}</a>
                        <div class="col-md-12 text-left">
                            <p>${taskRequest.task.description}</p>
                        </div>
                    </div>
                    <div class="col-md-4 text-right">
                        <c:if test="${taskRequest.price > 0}">
                            <h2>$${taskRequest.price}</h2>
                        </c:if>
                        <c:if test="${taskRequest.price == 0}">
                            <h4>По договоренности</h4>
                        </c:if>
                    </div>
                    <div class="col-md-8 text-left">
                        <span><b>Создан:</b> ${taskRequest.createDate}</span>
                    </div>
                    <div class="col-md-4 text-right">
                        <span><b>Статус:</b> ${taskRequest.taskRequestStatus.displayName}</span>
                    </div>
                </div>
                <div class="col-md-offset-1 col-md-10 line-separator"></div>
            </c:forEach>
        </div>
    </c:if>
</div>
<div class="col-md-12 footer-expand"></div>
</body>
</html>
