<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <div class="col-6">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-8">
                                <h4 class="mb-0"><span><spring:message
                                        code="securityPage.title"/></span></h4>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-12">
                                <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded mb-1"
                                   href="<c:url value='/edit/security/password-change'/>">
                                    <i class="icon-unlock icon-large nav-profile-side-icon"></i>
                                    <span><spring:message code="securityPage.password"/></span></a>
                                <a class="list-group-item list-group-item-action list-group-item-light border-0 rounded mb-1 d-flex"
                                   href="<c:url value='/edit/security/email-change'/>">
                                    <i class="icon-envelope icon-large nav-profile-side-icon"></i>
                                    <span class="col-6"><spring:message
                                            code="securityPage.email"/></span>
                                    <span class="col-6">${profile.user.email}</span>
                                </a>
                                <a class="list-group-item list-group-item-action list-group-item-danger border-0 rounded mb-1 delete-link"
                                   data-bs-toggle="modal" data-bs-target="#delete-account" href="#">
                                    <i class="icon-archive icon-large nav-profile-side-icon"></i>
                                    <span><spring:message code="securityPage.deleteAccount"/></span>
                                </a>
                                <div class="modal fade" id="delete-account" tabindex="-1">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title"><spring:message
                                                        code="securityPage.deleteAccount.modal.title"/></h5>
                                                <button type="button" class="btn-close"
                                                        data-bs-dismiss="modal"
                                                        aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body d-flex justify-content-center">
                                                <p><spring:message
                                                        code="securityPage.deleteAccount.modal.text"/></p>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary"
                                                        data-bs-dismiss="modal">
                                                    <spring:message code="button.cancel"/>
                                                </button>
                                                <a href="<c:url value='/edit/security/delete-profile'/>">
                                                    <button type="button" class="btn btn-danger">
                                                        <spring:message code="button.delete"/></button>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="edit-nav.jsp"/>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>
