<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand mb-4">
    <div class="container">
        <a class="navbar-brand" href="<c:url value='/'/>">Social Network</a>
        <sec:authorize access="isAuthenticated()">
            <div class="collapse navbar-collapse">
                <div class="navbar-nav ms-auto">
                    <div class="nav-username h-100">
                        <p class="m-0"><sec:authentication property="name"/></p>
                    </div>
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link nav-profile" href="#" id="navbarDropdown"
                               role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="icon-user icon-2x"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end"
                                aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">
                                    <spring:message code="nav.profile"/>
                                </a></li>
                                <li><a class="dropdown-item" href="<c:url value='/edit/profile'/>">
                                    <spring:message code="nav.settings"/>
                                </a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li class="dropdown-item text-center">
                                    <form action="${pageContext.request.contextPath}/logout"
                                          method="POST">
                                        <input type="hidden"
                                               name="${_csrf.parameterName}"
                                               value="${_csrf.token}"/>
                                        <button type="submit"
                                                class="dropdown-item p-0">
                                            <i class="icon-signout icon-large"></i>
                                            <span class="p-4"><spring:message
                                                    code="nav.signOut"/></span>
                                        </button>
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </sec:authorize>
    </div>
</nav>