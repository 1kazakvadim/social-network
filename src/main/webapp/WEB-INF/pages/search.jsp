<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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

            <div class="col-8 mb-4">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12">
                                    <h4><span><spring:message code="searchPage.title"/></span></h4>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-12">
                                    <form method="get" action="search">
                                        <div class="d-flex justify-content-center w-100">
                                            <i class="d-inline icon-search icon-large align-middle p-2 me-2"></i>
                                            <input type="text" name="search"
                                                   class="form-control w-100"
                                                   aria-label="full-name"
                                                   aria-describedby="inputGroup-sizing-default">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="row">
                                <c:if test="${not empty profiles}">
                                    <c:forEach items="${profiles}" var="profile">
                                        <div class="col-11 mb-2 p-2">
                                            <div class="d-flex p-3">
                                                <div>
                                                    <a class="friend-img-wrap d-flex"
                                                       href="<c:url value="/id${profile.user.id}"/>">
                                                        <img class="rounded-circle"
                                                             src="https://social-network-sam.s3.eu-north-1.amazonaws.com/${profile.profilePhotoName}"
                                                             alt="">
                                                    </a>
                                                </div>
                                                <div class="friend-details">
                                                    <h5 class="mb-1"><a
                                                            href="<c:url value="/id${profile.user.id}"/>"
                                                            class="user-title text-black">${profile.basicInformation.firstname} ${profile.basicInformation.lastname}</a>
                                                    </h5>
                                                    <a href="<c:url value="/messages/${profile.id}"/>"
                                                       class="text-secondary"><spring:message
                                                            code="searchPage.writeMessage"/></a>
                                                </div>
                                            </div>
                                        </div>
                                        <hr>
                                    </c:forEach>
                                </c:if>
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
