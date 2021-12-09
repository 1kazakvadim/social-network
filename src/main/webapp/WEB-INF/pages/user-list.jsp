<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="style" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style:styles/>
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
                        <span><spring:message code="nav.profile"/></span>
                    </a>
                    <a class="list-group-item list-group-item-action list-group-item-light border-0"
                       href="#">
                        <i class="icon-comment-alt icon-large"></i>
                        <span><spring:message code="nav.messages"/></span>
                        <span class="badge bg-primary rounded-pill">14</span>
                    </a>
                    <a class="list-group-item list-group-item-action list-group-item-light border-0"
                       href="#">
                        <i class="icon-group icon-large"></i>
                        <span><spring:message code="nav.friends"/></span>
                        <span class="badge bg-primary rounded-pill">7</span>
                    </a>
                    <a class="list-group-item list-group-item-action list-group-item-light border-0"
                       href="#">
                        <i class="icon-picture icon-large"></i>
                        <span><spring:message code="nav.photos"/></span>
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
                            <th scope="col"><spring:message code="user.email"/></th>
                            <th scope="col"><spring:message code="user.role"/></th>
                            <th scope="col"><spring:message code="user.isLocked"/></th>
                            <th scope="col"><spring:message code="user.timeRegistration"/></th>
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
                                        <spring:message code="user.isLocked.yes"/>
                                    </c:when>
                                    <c:otherwise>
                                        <spring:message code="user.isLocked.no"/>
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

<style:scripts/>

</body>
</html>
