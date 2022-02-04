<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-2">
    <div class="row">
        <div class="list-group list-group-flush border rounded p-1">
            <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded "
               href="<c:url value='/edit/profile'/>">
                <span><spring:message code="editNav.profile"/></span>
            </a>
            <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
               href="<c:url value='/edit/basic'/>">
                <span><spring:message code="editNav.basic"/></span>
            </a>
            <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded"
               href="<c:url value='/edit/security'/>">
                <span><spring:message code="editNav.security"/></span>
            </a>
        </div>
    </div>
</div>
