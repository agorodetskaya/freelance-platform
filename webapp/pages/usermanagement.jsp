<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Management</title>
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
<div class="add">
    <a class="btn btn-primary btn-sm href" href="register">Добавить нового пользователя</a>
</div>
<c:choose>
    <c:when test="${users.size()!=0}">
        <div>
            <c:if test="${param.error}">
                <div class="col-md-offset-4 col-md-5">* невозможно изменить пользователя</div>
            </c:if>
            <table class="table table-hover" align="center" title="Users">
                <tr>
                    <td class="thead-users">Id</td>
                    <td class="thead-users">Логин</td>
                    <td class="thead-users">Имя</td>
                    <td class="thead-users">Фамилия</td>
                    <td class="thead-users">Email</td>
                    <td class="thead-users">Возраст</td>
                    <td class="thead-users">Роль</td>
                    <td class="thead-users" colspan="2">Действие</td>
                </tr>
                <c:forEach var="user" items="${users}">
                <tr>
                    <form:form modelAttribute="userView" method="post" action="usermanagement/update">
                        <form:errors path="id"/>
                        <td class="tr-users"><form:input class="input input-id" path="id" value="${user.id}"
                                                         readonly="true" required="true"/></td>
                        <form:errors path="login"/>
                        <td class="tr-users"><form:input class="input" path="login" value="${user.login}"
                                                         required="true" maxlength="100"/></td>
                        <form:errors path="firstName"/>
                        <td class="tr-users"><form:input class="input" path="firstName" value="${user.firstName}"
                                                         required="true" maxlength="100"/></td>
                        <form:errors path="lastName"/>
                        <td class="tr-users"><form:input class="input" path="lastName" value="${user.lastName}"
                                                         required="true" maxlength="100"/></td>
                        <form:errors path="email"/>
                        <td class="tr-users"><form:input class="input" path="email" value="${user.email}"
                                                         required="true" type="email"/></td>
                        <form:errors path="age"/>
                        <td class="tr-users"><form:input class="input" path="age" value="${user.age}"
                                                         required="true" type="number" min="1"
                                                         max="150"/></td>
                        <td class="tr-users">
                            <form:errors path="roleName"/>
                            <form:select class="input" path="roleName" required="true">
                                <c:forEach items="${roles}" var="role">
                                    <option value="${role.name}"
                                        ${role.name == user.role.name ? 'selected' : ''}>${role.name}</option>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td class="tr-users">
                            <input class="btn btn-primary btn-sm button" type="submit" value="Изменить"/>
                        </td>
                    </form:form>
                    <td class="tr-users">
                        <a href="<c:url value="/usermanagement/delete/${user.id}"/>" class="btn btn-default btn-sm button">Удалить</a>
                    </td>
                </tr>
                </c:forEach>
        </div>
        </table>
    </c:when>
    <c:otherwise>
        <p class="empty-list" align="center">List is empty. Please add a new user.</p>
    </c:otherwise>
</c:choose>
<div class="col-md-12 footer-expand"></div>
</body>
</html>