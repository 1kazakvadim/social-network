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
            <div class="col-sm-8">
                <div class="container">
                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="d-flex flex-column align-items-center text-center">
                                        <div class="profile-image-wrap">
                                            <img src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${profile.profilePhotoName}"/>"
                                                 class="rounded-circle" alt="profile-photo">
                                            <div class="profile-image-action list-group">
                                                <a href="#" data-bs-toggle="modal"
                                                   data-bs-target="#upload-profile-photo">
                                                    <i class="icon-upload"></i>
                                                    <span><spring:message
                                                            code="profilePage.uploadPhoto"/></span>
                                                </a>
                                                <div class="modal fade" id="upload-profile-photo" tabindex="-1">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="upload-profile-photo-label">
                                                                    <spring:message
                                                                            code="uploadPage.title"/></h5>
                                                                <button type="button" class="btn-close"
                                                                        data-bs-dismiss="modal"
                                                                        aria-label="Close"></button>
                                                            </div>
                                                            <c:if test="${messageError != null}">
                                                                <div class="alert alert-danger" role="alert">
                                                                        ${messageError}
                                                                </div>
                                                            </c:if>
                                                            <div class="modal-body">
                                                                <form:form action="upload-profile-photo" method="POST" enctype="multipart/form-data">
                                                                    <input type="hidden"
                                                                           name="${_csrf.parameterName}"
                                                                           value="${_csrf.token}"/>
                                                                    <p><spring:message
                                                                            code="uploadPage.text"/></p>
                                                                    <input type="file" class="form-control"
                                                                           id="upload-photo" name="file"/>
                                                                    <button type="submit" class="btn btn-primary btn-sm mt-4">
                                                                        <span class="p-4"><spring:message
                                                                                code="button.upload"/></span>
                                                                    </button>
                                                                </form:form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <a href="<c:url value='/delete-profile-photo'/>">
                                                    <i class="icon-remove-sign"></i>
                                                    <span><spring:message
                                                            code="profilePage.deletePhoto"/></span>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="mt-3">
                                            <button class="btn btn-primary">
                                                <spring:message
                                                        code="profilePage.add"/></button>
                                            <button class="btn btn-primary"><spring:message
                                                    code="profilePage.message"/></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card mt-3">
                                <div class="hidden-xs hidden-sm">
                                    <ul class="profile-info-list">
                                        <li class="friend-title"><a href="#"><spring:message
                                                code="profilePage.friends"/> (${profile.friendCount})</a>
                                        </li>
                                        <li class="friend-img-list">
                                            <div class="friend-row">
                                                <a href="#"><img
                                                        src="<c:url value="/resources/static/images/friend1.png"/>"
                                                        alt=""/></a>
                                                <a href="#"><img
                                                        src="<c:url value="/resources/static/images/friend2.png"/>"
                                                        alt=""/></a>
                                                <a href="#"><img
                                                        src="<c:url value="/resources/static/images/friend3.png"/>"
                                                        alt=""/></a>
                                                <a href="#"><img
                                                        src="<c:url value="/resources/static/images/friend4.png"/>"
                                                        alt=""/></a>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card mt-3">
                                <ul class="list-group list-group-flush social-contact">
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <h6 class="mb-0">
                                            <i class="icon-github icon-large"></i>
                                            <span>Github</span>
                                        </h6>
                                        <c:if test="${profile.githubName != null}">
                                            <a class="text-secondary"
                                               href="#">${profile.githubName}</a>
                                        </c:if>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <h6 class="mb-0">
                                            <i class="icon-twitter icon-large"></i>
                                            <span>Twitter</span>
                                        </h6>
                                        <c:if test="${profile.twitterName != null}">
                                            <a class="text-secondary"
                                               href="#">${profile.twitterName}</a>
                                        </c:if>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <h6 class="mb-0">
                                            <i class="icon-instagram icon-large"></i>
                                            <span>Instagram</span>
                                        </h6>
                                        <c:if test="${profile.instagramName != null}">
                                            <a class="text-secondary"
                                               href="#">${profile.instagramName}</a>
                                        </c:if>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <h6 class="mb-0">
                                            <i class="icon-facebook icon-large"></i>
                                            <span>Facebook</span>
                                        </h6>
                                        <c:if test="${profile.facebookName != null}">
                                            <a class="text-secondary"
                                               href="#">${profile.facebookName}</a>
                                        </c:if>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <h6 class="mb-0">
                                            <i class="icon-skype icon-large"></i>
                                            <span>Skype</span>
                                        </h6>
                                        <c:if test="${profile.skypeName != null}">
                                            <a class="text-secondary"
                                               href="#">${profile.skypeName}</a>
                                        </c:if>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <h4 class="mb-0">
                                                <span>${profile.basicInformation.firstname} ${profile.basicInformation.lastname}</span>
                                            </h4>
                                        </div>
                                        <div class="col-sm-2 profile-edit">
                                            <c:if test="${user.id == profile.user.id}">
                                                <a class=""
                                                   href="<c:url value='/edit/profile'/>"><spring:message
                                                        code="profilePage.edit"/></a>
                                            </c:if>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row mb-2">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0"><span><spring:message
                                                    code="profilePage.email"/></span></h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <span>${profile.user.email}</span>
                                        </div>
                                    </div>
                                    <c:if test="${profile.homePhone != null}">
                                        <div class="row mb-2">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">
                                                <span><spring:message
                                                        code="profilePage.phone"/></span>
                                                </h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <span>${profile.homePhone}</span>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${profile.mobilePhone != null}">
                                        <div class="row mb-2">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">
                                                <span><spring:message
                                                        code="profilePage.mobile"/></span></h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <span>${profile.mobilePhone}</span>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${profile.country != null}">
                                        <div class="row mb-2">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">
                                                <span><spring:message
                                                        code="profilePage.country"/></span>
                                                </h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <span>${profile.country.name}</span>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${profile.city != null}">
                                        <div class="row mb-2">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">
                                                <span><spring:message
                                                        code="profilePage.city"/></span>
                                                </h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <span>${profile.city}</span>
                                            </div>
                                        </div>
                                    </c:if>
                                    <hr>
                                </div>
                            </div>
                            <div class="card mb-3">
                                <div class="card-body pt-0">
                                    <div class="row">
                                        <div>
                                            <div class="row">
                                                <div class="photo-title"><a href="#"><spring:message
                                                        code="profilePage.photos"/> (159)</a></div>
                                                <div class="col-lg-4 col-md-12 mb-4 mb-lg-0">
                                                    <div class="rounded">
                                                        <a href="#"><img
                                                                src="<c:url value="/resources/static/images/photo1.jpg"/>"
                                                                class="w-100"/></a>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 mb-4 mb-lg-0">
                                                    <div class="rounded">
                                                        <a href="#"><img
                                                                src="<c:url value="/resources/static/images/photo2.jpg"/>"
                                                                class="w-100"/></a>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 mb-4 mb-lg-0">
                                                    <div class="rounded">
                                                        <a href="#"><img
                                                                src="<c:url value="/resources/static/images/photo3.png"/>"
                                                                class="w-100"/></a>
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
