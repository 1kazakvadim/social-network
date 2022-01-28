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
                            <th scope="col"><spring:message code="profilesPage.timeRegistration"/></th>
                            <th scope="col"><spring:message code="profilesPage.timeUpdate"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${profiles}" var="profile">
                            <tr>
                                <td>${profile.user.id}<a
                                        href="<c:url value="profiles/${profile.id}/edit"/>"
                                        class="row-link"></a></td>
                                <td>${profile.basicInformation.firstname}<a
                                        href="<c:url value="profiles/${profile.id}/edit"/>"
                                        tabindex="-1"
                                        class="row-link"></a>
                                </td>
                                <td>${profile.basicInformation.lastname}<a
                                        href="<c:url value="profiles/${profile.id}/edit"/>"
                                        tabindex="-1"
                                        class="row-link"></a>
                                </td>
                                <td>${profile.user.email}<a
                                        href="<c:url value="profiles/${profile.id}/edit"/>"
                                        tabindex="-1"
                                        class="row-link"></a></td>
                                <td>${profile.user.role.name}<a
                                        href="<c:url value="profiles/${profile.id}/edit"/>"
                                        tabindex="-1"
                                        class="row-link"></a></td>
                                <td><c:choose>
                                    <c:when test="${profile.user.locked == true}">
                                        <spring:message code="profilesPage.isLocked.yes"/>
                                    </c:when>
                                    <c:otherwise>
                                        <spring:message code="profilesPage.isLocked.no"/>
                                    </c:otherwise>
                                </c:choose><a href="<c:url value="profiles/${profile.id}/edit"/>"
                                              tabindex="-1" class="row-link"></a></td>
                                <td>${profile.timeRegistration.toLocalDate()} ${profile.timeRegistration.toLocalTime()}<a
                                        href="<c:url value="profiles/${profile.id}/edit"/>"
                                        tabindex="-1" class="row-link"></a></td>
                                <td>${profile.updateTime.toLocalDate()} ${profile.updateTime.toLocalTime()}<a
                                        href="<c:url value="profiles/${profile.id}/edit"/>"
                                        tabindex="-1" class="row-link"></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>
