<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
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
                </sec:authorize>
                <li>
                    <a href="<c:url value="/index"/>">Задания</a>
                </li>
                <sec:authorize access="hasAuthority('CREATE NEW TASK')">
                    <li>
                        <a class="btn btn-primary" href="<c:url value="/newtask"/>">Создать Задание</a>
                    </li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="<c:url value="/logout"/>">Выйти</a>
                    </li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a class="btn btn-primary" href="<c:url value="/login"/>">Логин</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="col-md-offset-3 col-md-6 form">
    <form:form cssClass="form-horizontal" action="register" method="post" modelAttribute="user">
        <div class="form-group">
            <form:errors path="login"/>
            <label class="col-md-3 control-label">Логин:</label>
            <div class="col-md-9">
                <form:input cssClass="form-control" path="login" required="true" maxlength="100"/>
            </div>
        </div>
        <div class="form-group">
            <form:errors path="password"/>
            <label class="col-md-3 control-label">Пароль:</label>
            <div class="col-md-9">
                <form:input cssClass="form-control" type="password" path="password" required="true"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Повторите пароль:</label>
            <div class="col-md-9">
                <form:input cssClass="form-control" type="password" path="password2" required="true"/>
            </div>
        </div>
        <div class="form-group">
            <form:errors path="firstName"/>
            <label class="col-md-3 control-label">Имя:</label>
            <div class="col-md-9">
                <form:input cssClass="form-control" path="firstName" required="true" maxlength="100"/>
            </div>
        </div>
        <div class="form-group">
            <form:errors path="lastName"/>
            <label class="col-md-3 control-label">Фамилия:</label>
            <div class="col-md-9">
                <form:input cssClass="form-control" path="lastName" required="true" maxlength="100"/>
            </div>
        </div>
        <div class="form-group">
            <form:errors path="email"/>
            <label class="col-md-3 control-label">E-mail:</label>
            <div class="col-md-9">
                <form:input cssClass="form-control" type="email" path="email" required="true"/>
            </div>
        </div>
        <div class="form-group">
            <form:errors path="age"/>
            <label class="col-md-3 control-label">Возраст:</label>
            <div class="col-md-9">
                <form:input cssClass="form-control" type="number" path="age" required="true" min="1" max="150"
                            value="none"/>
            </div>
        </div>
        <sec:authorize access="hasAuthority('CREATE NEW USER')">
            <div class="form-group">
                <label class="col-md-3 control-label">Роль:</label>
                <div class="col-md-9">
                    <form:select cssClass="form-control" path="roleName">
                        <form:option value="" selected="true" disabled="true" hidden="true"
                                     label="Выберите роль..."/>
                        <c:forEach items="${roles}" var="roleName">
                            <form:option value="${roleName.name}" label="${roleName.name}"/>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
        </sec:authorize>
        <div class="form-group form-submit">
            <div class="col-md-9 col-md-offset-3">
                <input class="btn btn-primary" type="submit" value="Регистрация">
            </div>
        </div>
    </form:form>
</div>
<div class="col-md-12 footer-expand"></div>
</body>
</html>
