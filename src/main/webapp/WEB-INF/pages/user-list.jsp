<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="<c:url value="/resources/static/js/bootstrap.bundle.js"/>"></script>
    <link href="<c:url value="/resources/static/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/static/css/font-awesome/css/font-awesome.min.css"/>"
          rel="stylesheet">
    <link href="<c:url value="/resources/static/css/style.css"/>" rel="stylesheet"/>
    <script src="<c:url value="/resources/static/js/bootstrap.bundle.js"/>"></script>
    <title>Social Network</title>
</head>

<body class="d-flex flex-column min-vh-100">

<nav class="navbar navbar-expand mb-4">
    <div class="container-lg">
        <a class="navbar-brand" href="#">Social Network</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item mr-4">
                    <a class="nav-link nav-notification" href="#">
                        <i class="icon-bell-alt icon-2x">
                            <span class="badge bg-primary rounded-pill">23</span>
                        </i>
                    </a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link nav-profile" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="icon-user icon-2x"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">
                            <spring:message key="nav.profile"/>
                        </a></li>
                        <li><a class="dropdown-item" href="#">
                            <spring:message key="nav.settings"/>
                        </a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="#">
                            <spring:message key="nav.signOut"/>
                        </a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

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

<footer class="text-center text-lg-start mt-auto">
    <div class="text-center p-2 footer">
        <div class="dropup">
            <button type="button" class="btn btn-secondary dropdown-toggle language-selector"
                    data-bs-toggle="dropdown"
                    aria-expanded="false">
                <span><spring:message key="footer.language"/></span>
            </button>
            <ul class="dropdown-menu">
                <li>
                    <a class="dropdown-item" href="?lang=en">
                        <span><spring:message key="footer.language.english"/></span>
                        <span class="flag-icon flag-icon-us"></span>
                    </a>
                </li>
                <li>
                    <a class="dropdown-item" href="?lang=ru">
                        <span><spring:message key="footer.language.russian"/></span>
                        <span class="flag-icon flag-icon-ru"></span>
                    </a>
                </li>
            </ul>
            <span>© 2021 Vadim Kazak</span>
        </div>
    </div>
</footer>

<script src="<c:url value="/resources/static/js/bootstrap.bundle.js"/>"></script>

</body>
</html>
