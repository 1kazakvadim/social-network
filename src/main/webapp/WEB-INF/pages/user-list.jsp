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
            <div class="col-8">
                <div>
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><spring:message code="user.email"/></th>
                            <th scope="col"><spring:message code="user.role"/></th>
                            <th scope="col"><spring:message code="user.isLocked"/></th>
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
