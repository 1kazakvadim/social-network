<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authentication var="user" property="principal"/>

<div class="col-2 offset-1">
    <div class="list-group list-group-flush">
        <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
           href="<c:url value='/'/>">
            <i class="icon-user icon-large nav-profile-side-icon"></i>
            <span><spring:message code="nav.profile"/></span>
        </a>
        <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
           href="<c:url value='/id${user.id}/messages'/>">
            <i class="icon-comment-alt icon-large"></i>
            <span><spring:message code="nav.messages"/></span>
        </a>
        <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
           href="<c:url value='/id${user.id}/friends'/>">
            <i class="icon-group icon-large"></i>
            <span><spring:message code="nav.friends"/></span>
        </a>
        <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
           href="<c:url value='/id${user.id}/photos'/>">
            <i class="icon-picture icon-large"></i>
            <span><spring:message code="nav.photos"/></span>
        </a>
        <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
           href="<c:url value='/edit/profile'/>">
            <i class="icon-cog icon-large nav-profile-side-icon"></i>
            <span><spring:message code="nav.settings"/></span>
        </a>
        <sec:authorize access="hasRole('ADMIN')">
            <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
               href="<c:url value='/admin/profiles/'/>">
                <i class="icon-cogs icon-large "></i>
                <span><spring:message code="nav.administration"/></span>
            </a>
        </sec:authorize>
    </div>
</div>