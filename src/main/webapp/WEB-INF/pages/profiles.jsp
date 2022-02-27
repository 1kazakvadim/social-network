<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <jsp:include page="side-nav.jsp"/>
            <div class="col-9">
                <div>
                    <table class="table table-hover profile-table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><spring:message code="profilesPage.firstname"/></th>
                            <th scope="col"><spring:message code="profilesPage.lastname"/></th>
                            <th scope="col"><spring:message code="profilesPage.email"/></th>
                            <th scope="col"><spring:message code="profilesPage.role"/></th>
                            <th scope="col"><spring:message code="profilesPage.isLocked"/></th>
                            <th scope="col"><spring:message
                                    code="profilesPage.timeRegistration"/></th>
                            <th scope="col"><spring:message code="profilesPage.timeUpdate"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${profiles}" var="profile">
                            <tr>
                                <td>${profile.user.id}<a
                                        href="<c:url value="/admin/profiles/${profile.id}/edit"/>"
                                        class="row-link"></a></td>
                                <td class="name">${profile.basicInformation.firstname}<a
                                        href="<c:url value="/admin/profiles/${profile.id}/edit"/>"
                                        tabindex="-1"
                                        class="row-link"></a>
                                </td>
                                <td class="name">${profile.basicInformation.lastname}<a
                                        href="<c:url value="/admin/profiles/${profile.id}/edit"/>"
                                        tabindex="-1"
                                        class="row-link"></a>
                                </td>
                                <td class="name">${profile.user.email}<a
                                        href="<c:url value="/admin/profiles/${profile.id}/edit"/>"
                                        tabindex="-1"
                                        class="row-link"></a></td>
                                <td>${profile.user.role.name}<a
                                        href="<c:url value="/admin/profiles/${profile.id}/edit"/>"
                                        tabindex="-1"
                                        class="row-link"></a></td>
                                <td><c:choose>
                                    <c:when test="${profile.user.locked == true}">
                                        <spring:message code="profilesPage.isLocked.yes"/>
                                    </c:when>
                                    <c:otherwise>
                                        <spring:message code="profilesPage.isLocked.no"/>
                                    </c:otherwise>
                                </c:choose><a
                                        href="<c:url value="/admin/profiles/${profile.id}/edit"/>"
                                        tabindex="-1" class="row-link"></a></td>
                                <td>${profile.timeRegistration.toLocalDate()} ${profile.timeRegistration.toLocalTime()}<a
                                        href="<c:url value="/admin/profiles/${profile.id}/edit"/>"
                                        tabindex="-1" class="row-link"></a></td>
                                <td>${profile.updateTime.toLocalDate()} ${profile.updateTime.toLocalTime()}<a
                                        href="<c:url value="/admin/profiles/${profile.id}/edit"/>"
                                        tabindex="-1" class="row-link"></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <hr>
                    <div class="container">
                        <div class="row">
                            <div class="col-12">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled">
                                        <a href="#" class="page-link"
                                           tabindex="-1">
                                            <spring:message code="pages"/>
                                        </a>
                                    </li>
                                    <c:forEach begin="1"
                                               end="${total}"
                                               var="pageNumber">
                                        <c:choose>
                                            <c:when test="${page == pageNumber - 1}">
                                                <li class="page-item active">
                                                    <a class="page-link"
                                                       href="#">${pageNumber}</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item"><a
                                                        class="page-link"
                                                        tabindex="-1"
                                                        href="<c:url value="/admin/profiles?page=${pageNumber-1}&size=${size}"/>">${pageNumber}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="col-12">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled">
                                        <a href="#" class="page-link"
                                           tabindex="-1">
                                            <spring:message
                                                    code="elementsOnPage"/>
                                        </a>
                                    </li>
                                    <c:forEach items="${elementsOnPage}"
                                               var="element">
                                        <c:choose>
                                            <c:when test="${element == size}">
                                                <li class="page-item active">
                                                    <a
                                                            class="page-link"
                                                            href="#">${element}</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item"><a
                                                        class="page-link"
                                                        tabindex="-1"
                                                        href="<c:url value="/admin/profiles?page=${page}&size=${element}"/>">${element}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>
