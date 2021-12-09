<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="<c:url value="/resources/static/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/static/css/font-awesome/css/font-awesome.min.css"/>"
          rel="stylesheet">
    <link href="<c:url value="/resources/static/css/style.css"/>" rel="stylesheet"/>
    <title>Social Network</title>
</head>

<body class="d-flex flex-column min-vh-100">

<jsp:include page="header.jsp"/>

<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-2">
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light border-0"
                       href="#">
                        <i class="icon-user icon-large nav-profile-side-icon"></i>
                        <span><spring:message key="nav.profile"/></span>
                    </a>
                    <a class="list-group-item list-group-item-action list-group-item-light border-0"
                       href="#">
                        <i class="icon-comment-alt icon-large"></i>
                        <span><spring:message key="nav.messages"/></span>
                        <span class="badge bg-primary rounded-pill">14</span>
                    </a>
                    <a class="list-group-item list-group-item-action list-group-item-light border-0"
                       href="#">
                        <i class="icon-group icon-large"></i>
                        <span><spring:message key="nav.friends"/></span>
                        <span class="badge bg-primary rounded-pill">7</span>
                    </a>
                    <a class="list-group-item list-group-item-action list-group-item-light border-0"
                       href="#">
                        <i class="icon-picture icon-large"></i>
                        <span><spring:message key="nav.photos"/></span>
                    </a>
                    <hr class="solid">
                </div>
            </div>
            <div class="col-sm-8">
                <div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><spring:message key="user.email"/></th>
                            <th scope="col"><spring:message key="user.role"/></th>
                            <th scope="col"><spring:message key="user.isLocked"/></th>
                            <th scope="col"><spring:message key="user.timeRegistration"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <th scope="row">${user.id}</th>
                                <td>${user.email}</td>
                                <td>${user.role.name}</td>
                                <td><c:choose>
                                    <c:when test="${user.locked == true}">
                                        <spring:message key="user.isLocked.yes"/>
                                    </c:when>
                                    <c:otherwise>
                                        <spring:message key="user.isLocked.no"/>
                                    </c:otherwise>
                                </c:choose></td>
                                <td>${user.timeRegistration}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp" />

<script src="<c:url value="/resources/static/js/bootstrap.bundle.min.js"/>"></script>

</body>
</html>
