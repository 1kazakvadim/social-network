<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
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