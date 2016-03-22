<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Task</title>
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
                <sec:authorize access="isAnonymous()">
                    <li>
                        <a class="btn btn-primary" href="<c:url value="/login"/>">Логин</a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<div class="col-md-offset-1 col-md-10">
    <div class="col-md-8">
        <h2>${task.theme}</h2>
    </div>
    <div class="col-md-4 text-right">
        <h3>${task.taskStatus.displayName}</h3>
    </div>
    <div class="col-md-12 line-separator"></div>
    <div class="col-md-12">
        <div class="col-md-12">
            <h3>Описание</h3>
            <span>${task.description}</span>
        </div>
        <div class="col-md-12">
            <h3>Стоимость</h3>
            <c:if test="${task.price > 0}">
                <span>$${task.price}</span>
            </c:if>
            <c:if test="${task.price == 0}">
                <span>По договоренности</span>
            </c:if>
        </div>
        <div class="col-md-12">
            <h3>Время на выполнение</h3>
            <c:if test="${task.estimateTimeDays > 0}">
                <span>${task.estimateTimeDays} дней </span>
            </c:if>
            <c:if test="${task.estimateTimeHours > 0}">
                <span>${task.estimateTimeHours} часов</span>
            </c:if>
            <c:if test="${task.estimateTimeDays == 0 && task.estimateTimeHours == 0}">
                <span>По договоренности</span>
            </c:if>
        </div>
    </div>
    <div class="col-md-12 line-separator"></div>
    <div class="col-md-12 text-left">
        <span class="col-md-4"><b>Создан:</b> ${task.createDate}</span>
        <span class="col-md-offset-4 col-md-4 text-right"><b>Просмотров:</b> ${task.visitCount}</span>
        <span class="col-md-4"><b>Автор:</b> ${task.customer.login}</span>
    </div>
    <div class="col-md-12 line-separator"></div>
    <div class="col-md-6">
        <h3 id="h3">Комментарии</h3>
        <c:if test="${comments.isEmpty()}">
            <span>Никто не оставил свой комментарий...</span>
        </c:if>
        <c:forEach var="comment" items="${comments}">
            <span class="col-md-12 h4">${comment.content}</span>
            <div class="col-md-4"> ${comment.createDate} </div>
            <div class="col-md-offset-4 col-md-4"> ${comment.author.login} </div>
            <div class="col-md-12 line-separator"></div>
        </c:forEach>
    </div>
    <div class="col-md-6">
        <h3 id="h3">Отклики</h3>
        <sec:authorize access="isAuthenticated()">
            <c:if test="${user.id == task.customer.id}">
                <c:if test="${task.taskRequests.isEmpty()}">
                    <span>Никто не откликнулся на ваш заказ...</span>
                </c:if>
            </c:if>
            <c:forEach var="taskRequest" items="${task.taskRequests}">
                <div class="col-md-offset-1 col-md-10">
                    <div class="col-md-8">
                        <span>${taskRequest.executor.login}</span>
                        <div class="col-md-12 text-left">
                            <p>${taskRequest.comment}</p>
                        </div>
                    </div>
                    <div class="col-md-12 text-right">
                        <c:if test="${taskRequest.estimateTimeDays > 0}">
                            <span>${task.estimateTimeDays} дней </span>
                        </c:if>
                        <c:if test="${taskRequest.estimateTimeHours > 0}">
                            <span>${task.estimateTimeHours} часов</span>
                        </c:if>
                        <c:if test="${taskRequest.estimateTimeDays == 0 && taskRequest.estimateTimeHours == 0}">
                            <span>По договоренности</span>
                        </c:if>
                    </div>
                    <div class="col-md-12 text-right">
                        <c:if test="${taskRequest.price > 0}">
                            <h2>$${taskRequest.price}</h2>
                        </c:if>
                        <c:if test="${taskRequest.price == 0}">
                            <h4>По договоренности</h4>
                        </c:if>
                    </div>
                    <div class="col-md-8 text-left">
                        <span><b>Отправлена:</b> ${taskRequest.createDate}</span>
                    </div>
                    <div class="col-md-4 text-right">
                        <span><b>Статус:</b> ${taskRequest.taskRequestStatus.displayName}</span>
                    </div>
                    <c:if test="${user.id == task.customer.id}">
                        <c:if test="${taskRequest.taskRequestStatus.displayName != 'выбран'}">
                            <div class="col-md-6">
                                <a href="<c:url value="/task/${task.id}/accept/${taskRequest.id}"/>"
                                   class="btn btn-default">Выбрать исполнителя</a>
                            </div>
                        </c:if>
                    </c:if>
                    <c:if test="${task.taskStatus.displayName == 'открыт' || task.taskStatus.displayName == 'есть предложения'}">
                        <div class="col-md-6">
                            <a href="<c:url value="/task/${task.id}/delete/${taskRequests.get(0).id}"/>"
                               class="btn btn-default">Удалить заявку</a>
                        </div>
                    </c:if>
                </div>
                <div class="col-md-offset-1 col-md-10 line-separator"></div>
            </c:forEach>
            <%--</c:if>--%>
            <c:if test="${user.id != task.customer.id}">
                <c:if test="${leaveComment}">
                    <form class="form-horizontal" action="<c:url value="/task/${task.id}/comment"/>" method="post">
                        <div class="form-group">
                            <label class="col-md-2 control-label">Сообщение</label>
                            <div class="col-md-10">
                                <textarea class="form-control" name="commentContent" required></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-10 col-md-offset-2">
                                <input class="btn btn-primary" type="submit" value="Отправить">
                                <a href="<c:url value="/task/${task.id}"/>" class="btn btn-default">Отмена</a>
                            </div>
                        </div>
                    </form>
                </c:if>
                <sec:authorize access="hasAuthority('CREATE NEW TASK REQUEST')">
                    <c:if test="${leaveTaskRequest}">
                        <form:form class="form-horizontal" action="/task/${task.id}/taskrequest" method="post"
                                   modelAttribute="taskRequest">
                            <div class="form-group">
                                <form:errors path="comment"/>
                                <label class="col-md-2 control-label">Сообщение</label>
                                <div class="col-md-10">
                                    <form:textarea type="text" class="form-control" path="comment"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:errors path="price"/>
                                <label class="col-md-2 control-label">Цена $</label>
                                <div class="col-md-10">
                                    <form:input type="number" class="form-control" path="price" required="true"
                                                min="0"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <form:errors path="estimateTimeDays"/>
                                <label class="col-md-2 control-label">Дней </label>
                                <div class="col-md-4">
                                    <form:input cssClass="form-control" id="estimateTimeDays" path="estimateTimeDays"
                                                type="number" min="0"/>
                                </div>
                                <form:errors path="estimateTimeHours"/>
                                <label class="col-md-2 control-label">Часов </label>
                                <div class="col-md-4">
                                    <form:input cssClass="form-control" id="estimateTimeHours" path="estimateTimeHours"
                                                type="number"
                                                min="0" max="23"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-10 col-md-offset-2">
                                    <input class="btn btn-primary" type="submit" value="Отправить">
                                    <a href="<c:url value="/task/${task.id}"/>" class="btn btn-default">Отмена</a>
                                </div>
                            </div>
                        </form:form>
                    </c:if>
                </sec:authorize>
                <c:if test="${!leaveComment && !leaveTaskRequest}">
                    <sec:authorize access="hasAuthority('CREATE NEW TASK REQUEST')">
                        <c:if test="${task.taskStatus.displayName == 'открыт' || task.taskStatus.displayName == 'есть предложения'}">
                            <div class="col-md-8 text-right">
                                <a href="<c:url value="/task/${task.id}/taskrequest"/>" class="btn btn-primary">Оставить
                                    заявку на выполнение</a>
                            </div>
                        </c:if>
                    </sec:authorize>
                    <sec:authorize access="hasAuthority('CREATE NEW COMMENT')">
                        <div class="col-md-4">
                            <a href="<c:url value="/task/${task.id}/comment"/>" class="btn btn-default">Задать
                                вопрос</a>
                        </div>
                    </sec:authorize>
                </c:if>
            </c:if>
            <c:if test="${user.id == task.executor.id}">
                <span class="col-md-12">Вы взялись выполнять это задание. Сообщить заказчику о выполнении?</span>
                <div class="col-md-6">
                    <a href="<c:url value="/task/${task.id}/submit"/>" class="btn btn-primary">Выполнить задание</a>
                </div>
            </c:if>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <span>Чтобы оставить заявку на выполнение этого задания вы должны зарегистрироваться.</span>
        </sec:authorize>
    </div>
</div>
<div class="col-md-12 footer-expand"></div>
</body>
</html>
