<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <style:styles/>
    <title>Social Network</title>
</head>

<body class="d-flex flex-column min-vh-100">

<sec:authentication var="user" property="principal"/>
<jsp:include page="header.jsp"/>

<section>
    <div class="container">
        <div class="row">

            <jsp:include page="side-nav.jsp"/>

            <div class="col-9 mb-4">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12">
                                    <h4><span><spring:message
                                            code="messagePage.title"/></span></h4>
                                </div>
                            </div>
                            <hr>
                            <div class="container ps-0 pe-0">
                                <div class="row gutters">
                                    <div class="col-12">
                                        <div class="row no-gutters">
                                            <div class="col-5">
                                                <c:choose>
                                                    <c:when test="${not empty profiles}">
                                                        <div class="users-container overflow-auto">
                                                            <ul class="users">
                                                                <c:forEach items="${profiles}"
                                                                           var="profile">
                                                                    <li class="person mb-1 p-1">
                                                                        <div class="row m-0">
                                                                            <div class="col-11">
                                                                                <a class="d-inline-block user d-flex h-100"
                                                                                   href="<c:url value="/messages/${profile.id}"/>">
                                                                                    <div class="dialog-photo-wrap me-2">
                                                                                        <img class="rounded-circle"
                                                                                             src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${profile.profilePhotoName}"/>">
                                                                                    </div>
                                                                                    <div class="name d-flex">
                                                                                        <p>
                                                                                                ${profile.basicInformation.firstname} ${profile.basicInformation.lastname}
                                                                                        </p>
                                                                                    </div>
                                                                                </a>
                                                                            </div>
                                                                            <div class="col-1 p-0">
                                                                                <a class="text-secondary delete-dialog-link"
                                                                                   data-bs-toggle="modal"
                                                                                   data-bs-target="#delete-dialog"
                                                                                   href="#">&#10005;</a>
                                                                            </div>
                                                                            <div class="modal fade"
                                                                                 id="delete-dialog"
                                                                                 tabindex="-1">
                                                                                <div class="modal-dialog modal-dialog-centered">
                                                                                    <div class="modal-content">
                                                                                        <div class="modal-header">
                                                                                            <h5 class="modal-title">
                                                                                                <spring:message
                                                                                                        code="messagePage.deleteDialog.modal.title"/></h5>
                                                                                            <button type="button"
                                                                                                    class="btn-close"
                                                                                                    data-bs-dismiss="modal"
                                                                                                    aria-label="Close"></button>
                                                                                        </div>
                                                                                        <div class="modal-body d-flex justify-content-center">
                                                                                            <p>
                                                                                                <spring:message
                                                                                                        code="messagePage.deleteDialog.modal.text"/></p>
                                                                                        </div>
                                                                                        <div class="modal-footer">
                                                                                            <button type="button"
                                                                                                    class="btn btn-secondary"
                                                                                                    data-bs-dismiss="modal">
                                                                                                <spring:message
                                                                                                        code="button.cancel"/>
                                                                                            </button>
                                                                                            <a href="<c:url value="/messages/${profile.id}/delete-dialog" />">
                                                                                                <button type="button"
                                                                                                        class="btn btn-danger">
                                                                                                    <spring:message
                                                                                                            code="button.delete"/></button>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <div class="d-flex flex-column justify-content-center h-100 align-middle">
                                                            <div class="d-inline text-secondary">
                                                                <i class="icon-comments icon-3x d-block d-flex justify-content-center"></i>
                                                                <span class="d-block d-flex justify-content-center">
                                                                   <spring:message
                                                                           code="messagePage.noDialogs"/>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="col-7">
                                                <div class="card">
                                                    <div class="chat-container overflow-auto d-flex flex-column min-vh-100 justify-content-center text-center">
                                                        <p class="text-secondary">
                                                            <spring:message
                                                                    code="messagePage.emptyMessages"/></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
