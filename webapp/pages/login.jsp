<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
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
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a class="btn btn-primary" href="<c:url value="/register"/>">Регистрация</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="col-md-offset-3 col-md-6 form">
    <form class="form-horizontal" action="login" method="post">
        <c:if test="${param.error}">
            <div class="col-md-offset-2 col-md-10">* неправильный логин или пароль</div>
        </c:if>
        <div class="form-group">
            <label class="col-md-2 control-label">Логин</label>
            <div class="col-md-10">
                <input class="form-control" name="username" type="text" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-2 control-label">Пароль</label>
            <div class="col-md-10">
                <input type="password" class="form-control" name="password" maxlength="25" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-10 col-md-offset-2">
                <input class="btn btn-primary" type="submit" value="Войти">
            </div>
        </div>
    </form>
</div>
<div class="col-md-12 footer-expand"></div>
</body>
</html>