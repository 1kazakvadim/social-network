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

<section>
    <div class="container">
        <div class="row">

            <jsp:include page="side-nav.jsp"/>
            <div class="col-9 mb-4">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12">
                                    <h4><span>Messages</span></h4>
                                </div>
                            </div>
                            <hr>
                            <div class="container ps-0 pe-0">
                                <div class="row gutters">
                                    <div class="col-12">
                                        <div class="row no-gutters">
                                            <div class="col-4">
                                                <div class="users-container overflow-auto">
                                                    <ul class="users">
                                                        <c:forEach items="${profiles}"
                                                                   var="profile">
                                                            <li class="person pb-1">
                                                                <a href="<c:url value="/messages/${profile.id}"/>">
                                                                    <div class="user photo-wrap">
                                                                        <img src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${profile.profilePhotoName}"/>">
                                                                    </div>
                                                                    <p class="name-time">
                                                                        <span class="name">${profile.basicInformation.firstname} ${profile.basicInformation.lastname}</span>
                                                                    </p>
                                                                </a>
                                                            </li>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="col-8">
                                                <div class="card">
                                                    <div class="chat-container overflow-auto d-flex flex-column min-vh-100">
                                                        <ul class="chat-box p-3">
                                                            <c:forEach items="${messages}"
                                                                       var="message">
                                                                <li class="chat-left">
                                                                    <div class="chat-avatar">
                                                                        <a href="<c:url value="/id${message.messageSender.user.id}"/>">
                                                                            <img src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${message.messageSender.profilePhotoName}" />">
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
                                                                        <div class="chat-text">
                                                                            <p>${message.messageText}</p>
                                                                        </div>
                                                                    </div>
                                                                </li>
                                                            </c:forEach>
                                                        </ul>
                                                        <div class="form-group p-2 mt-auto">
                                                            <form:form method="POST"
                                                                       modelAttribute="messageDto"
                                                                       action="${profileId}/send-message">
                                                                <div>
                                                                    <form:input type="hidden"
                                                                                value="${messageSenderProfileId}"
                                                                                path="messageSenderProfileId"/>
                                                                    <form:input type="hidden"
                                                                                value="${dialogId}"
                                                                                path="dialogId"/>
                                                                    <form:input class="form-control" min="1"
                                                                                maxlength="255"
                                                                                rows="3"
                                                                                aria-label="message-text"
                                                                                name="messageText"
                                                                                id="message-text"
                                                                                path="messageText"/>
                                                                </div>
                                                                <button type="submit"
                                                                        class="btn btn-primary btn-sm mt-4">
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

</body>
</html>
