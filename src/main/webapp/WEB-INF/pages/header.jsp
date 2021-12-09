<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
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