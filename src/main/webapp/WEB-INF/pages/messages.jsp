<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style:styles/>
    <title>Social Network</title>
</head>

<body class="d-flex flex-column min-vh-100">

<sec:authentication var="user" property="principal"/>
<jsp:include page="header.jsp"/>

<section class="min-vh-100">
    <div class="container">
        <div class="row">

            <jsp:include page="side-nav.jsp"/>

            <div class="col-9 mb-4">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12">
                                    <h4><span><spring:message code="messagePage.title"/></span></h4>
                                </div>
                            </div>
                            <hr>
                            <div class="container h-100 ps-0 pe-0">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="row">
                                            <div class="col-5">
                                                <div class="d-flex min-vh-100 users-container overflow-auto">
                                                    <ul class="users">
                                                        <c:forEach items="${profiles}"
                                                                   var="profile">
                                                            <li class="person mb-1 p-1">
                                                                <div class="row m-0">
                                                                    <div class="col-11">
                                                                        <a class="d-inline-block user d-flex h-100"
                                                                           href="<c:url value="/messages/${profile.id}"/>">
                                                                            <div class="dialog-photo-wrap me-2">
                                                                                <img class="rounded-circle"
                                                                                     src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${profile.profilePhotoName}"/>">
                                                                            </div>
                                                                            <div class="name d-flex">
                                                                                <p>
                                                                                        ${profile.basicInformation.firstname} ${profile.basicInformation.lastname}
                                                                                </p>
                                                                            </div>
                                                                        </a>
                                                                    </div>
                                                                    <div class="col-1 p-0">
                                                                        <a class="text-secondary delete-dialog-link"
                                                                           data-bs-toggle="modal"
                                                                           data-bs-target="#delete-dialog"
                                                                           href="#">&#10005;</a>
                                                                    </div>
                                                                    <div class="modal fade"
                                                                         id="delete-dialog"
                                                                         tabindex="-1">
                                                                        <div class="modal-dialog modal-dialog-centered">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <h5 class="modal-title">
                                                                                        <spring:message
                                                                                                code="messagePage.deleteDialog.modal.title"/></h5>
                                                                                    <button type="button"
                                                                                            class="btn-close"
                                                                                            data-bs-dismiss="modal"
                                                                                            aria-label="Close"></button>
                                                                                </div>
                                                                                <div class="modal-body d-flex justify-content-center">
                                                                                    <p>
                                                                                        <spring:message
                                                                                                code="messagePage.deleteDialog.modal.text"/></p>
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="button"
                                                                                            class="btn btn-secondary"
                                                                                            data-bs-dismiss="modal">
                                                                                        <spring:message
                                                                                                code="button.cancel"/>
                                                                                    </button>
                                                                                    <a href="<c:url value="/messages/${profile.id}/delete-dialog" />">
                                                                                        <button type="button"
                                                                                                class="btn btn-danger">
                                                                                            <spring:message
                                                                                                    code="button.delete"/></button>
                                                                                    </a>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-7">
                                                <div class="card">
                                                    <div class="chat-container min-vh-100">
                                                        <div class="d-flex selected-user">
                                                            <span class="pt-2 ps-2 ">
                                                                <spring:message
                                                                        code="messagePage.to"/>
                                                                <a class="text-secondary"
                                                                   href="<c:url value="/id${recipientProfile.user.id}"/>">
                                                                ${recipientProfile.basicInformation.firstname} ${recipientProfile.basicInformation.lastname}
                                                            </a>
                                                            </span>
                                                        </div>
                                                        <hr>
                                                        <div class="min-vh-100 overflow-auto">
                                                            <ul class="chat-box vh-100 p-3 overflow-auto"
                                                                id="chat-box">
                                                                <c:forEach items="${messages}"
                                                                           var="message">
                                                                    <li class="chat-left">
                                                                        <div class="chat-avatar">
                                                                            <a href="<c:url value="/id${message.messageSender.user.id}"/>">
                                                                                <div class="chat-photo-wrap">
                                                                                    <img src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${message.messageSender.profilePhotoName}"/>">
                                                                                </div>
                                                                                <div class="chat-name">
                                                                                        ${message.messageSender.basicInformation.firstname}
                                                                                </div>
                                                                            </a>
                                                                        </div>
                                                                        <div>
                                                                            <div class="chat-hour">${message.timeCreation.toLocalTime()} ${message.timeCreation.toLocalDate()}
                                                                                <span
                                                                                        class="fa fa-check-circle"></span>
                                                                            </div>
                                                                            <c:choose>
                                                                                <c:when test="${message.messageSender.user.id eq user.id}">
                                                                                    <div class="chat-text"
                                                                                         style="background-color: #daf7e6">
                                                                                        <p>${message.messageText}</p>
                                                                                    </div>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <div class="chat-text"
                                                                                         style="background-color: #f4f5fb">
                                                                                        <p>${message.messageText}</p>
                                                                                    </div>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                        <div class="form-group p-2 mt-auto">
                                                            <form:form method="POST" id="message"
                                                                       modelAttribute="messageDto"
                                                                       action="${profileId}/send-message">
                                                                <div>
                                                                    <form:input type="hidden"
                                                                                value="${messageSenderProfileId}"
                                                                                path="messageSenderProfileId"/>
                                                                    <form:input type="hidden"
                                                                                value="${dialogId}"
                                                                                path="dialogId"/>
                                                                    <form:input class="form-control"
                                                                                min="1"
                                                                                maxlength="255"
                                                                                rows="3"
                                                                                aria-label="message-text"
                                                                                name="messageText"
                                                                                id="message-text"
                                                                                path="messageText"/>
                                                                </div>
                                                                <button type="submit" id="send"
                                                                        class="btn btn-primary btn-sm mt-2">
                                                                <span class="p-4"><spring:message
                                                                        code="button.send"/></span>
                                                                </button>
                                                            </form:form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>
<style:messageScroll/>

</body>
</html>
