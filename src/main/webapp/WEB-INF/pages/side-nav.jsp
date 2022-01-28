<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<sec:authentication var="user" property="principal"/>

<div class="col-sm-2 offset-sm-1">
    <div class="list-group list-group-flush">
        <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
           href="<c:url value='/'/>">
            <i class="icon-user icon-large nav-profile-side-icon"></i>
            <span><spring:message code="nav.profile"/></span>
        </a>
        <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
           href="#">
            <i class="icon-comment-alt icon-large"></i>
            <span><spring:message code="nav.messages"/></span>
            <span class="badge bg-primary rounded-pill">14</span>
        </a>
        <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
           href="<c:url value='/id${user.id}/friends'/>">
            <i class="icon-group icon-large"></i>
            <span><spring:message code="nav.friends"/></span>
            <span class="badge bg-primary rounded-pill">7</span>
        </a>
        <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
           href="<c:url value='/id${profile.id}/photos'/>">
            <i class="icon-picture icon-large"></i>
            <span><spring:message code="nav.photos"/></span>
        </a>
        <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
           href="<c:url value='/edit/profile'/>">
            <i class="icon-cog icon-large nav-profile-side-icon"></i>
            <span><spring:message code="nav.settings"/></span>
        </a>
        <sec:authorize access="hasRole('ADMIN')">
            <a class="list-group-item list-group-item-action list-group-item-light border-0 dropdown-toggle"
               data-bs-toggle="collapse" href="#" role="button" aria-expanded="false"
               aria-controls="collapseMenu">
                <i class="icon-cogs icon-large "></i>
                <span><spring:message code="nav.administration"/></span>
            </a>
            <div class="collapse" id="collapseMenu">
                <div>
                    <ul class="nav-administration">
                        <li><a class="dropdown-item rounded"
                               href="<c:url value='/admin/profiles'/>"><spring:message
                                code="nav.users"/></a></li>
                    </ul>
                </div>
            </div>
        <hr class="solid">
        </sec:authorize>
    </div>
</div>