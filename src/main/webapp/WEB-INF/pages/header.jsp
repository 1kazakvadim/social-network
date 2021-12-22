<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand mb-4">
    <div class="container-lg">
        <a class="navbar-brand" href="<c:url value='/profile'/>">Social Network</a>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav ms-auto">
                <sec:authorize access="isAuthenticated()">
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
                        <ul class="dropdown-menu dropdown-menu-end"
                            aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">
                                <spring:message code="nav.profile"/>
                            </a></li>
                            <li><a class="dropdown-item" href="#">
                                <spring:message code="nav.settings"/>
                            </a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="<c:url value='/logout'/>">
                                <spring:message code="nav.signOut"/>
                            </a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>