<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create task</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
    <script type="text/javascript">
        function toggleEstimatedTime() {
            var estimateTimeDays = document.getElementById("estimateTimeDays");
            var estimateTimeHours = document.getElementById("estimateTimeHours");
            estimateTimeDays.disabled = !estimateTimeDays.disabled;
            estimateTimeHours.disabled = !estimateTimeHours.disabled;
        }
        function togglePrice() {
            var price = document.getElementById("price");
            price.disabled = !price.disabled;
        }
    </script>
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
                <li>
                    <a class="btn btn-primary" href="<c:url value="/index"/>">Задания</a>
                </li>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="<c:url value="/logout"/>">Выйти</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<div class="col-md-offset-3 col-md-6 form">
    <form:form cssClass="form-horizontal" action="newtask" method="post" modelAttribute="task">
        <div class="form-group">
            <form:errors path="theme"/>
            <label class="col-md-2 control-label">Тема </label>
            <div class="col-md-10">
                <form:input cssClass="form-control" path="theme" required="true"/>
            </div>
        </div>
        <div class="form-group">
            <form:errors path="description"/>
            <label class="col-md-2 control-label">Описание </label>
            <div class="col-md-10">
                <form:textarea id="textarea-size" cssClass="form-control" path="description" required="true"/>
            </div>
        </div>
        <fieldset class="form-fieldset">
            <legend class="form-legend">Стоимость</legend>
            <div class="form-group">
                <form:errors path="price"/>
                <label class="col-md-3 control-label">Цена $</label>
                <div class="col-md-9">
                    <form:input cssClass="form-control" path="price" type="number" min="0"/>
                </div>
                <div class="col-md-offset-4">
                    <form:checkbox cssClass="checkbox-inline" id="priceAgreement" path="priceAgreement"
                                   onclick="togglePrice()"/>
                    <label class="text-left"> по договоренности</label>
                </div>
            </div>
        </fieldset>
        <fieldset class="form-fieldset">
            <legend class="form-legend">Время выполнения</legend>
            <div class="form-group">
                <form:errors path="estimateTimeDays"/>
                <label class="col-md-3 control-label">Дней </label>
                <div class="col-md-3">
                    <form:input cssClass="form-control" id="estimateTimeDays" path="estimateTimeDays" type="number"
                                min="0"/>
                </div>
                <form:errors path="estimateTimeHours"/>
                <label class="col-md-3 control-label">Часов </label>
                <div class="col-md-3">
                    <form:input cssClass="form-control" id="estimateTimeHours" path="estimateTimeHours"
                                type="number" min="0"
                                max="23"/>
                </div>
                <div class="col-md-offset-4">
                    <form:checkbox cssClass="checkbox-inline" id="estimateTimeAgreement"
                                   path="estimateTimeAgreement" onclick="toggleEstimatedTime()"/>
                    <label class="text-left"> по договоренности</label>
                </div>
            </div>
        </fieldset>
        <div class="form-group form-submit row">
            <div class="col-md-offset-4 col-md-4">
                <input class="btn btn-block btn-primary" type="submit" value="Создать">
            </div>
        </div>
    </form:form>
</div>
<div class="col-md-12 footer-expand"></div>
</body>
</html>
