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
            <div class="col-8">
                <div class="container">
                    <div class="row">
                        <div class="col-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="d-flex flex-column align-items-center text-center">
                                        <div class="profile-image-wrap">
                                            <img src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${profile.profilePhotoName}"/>"
                                                 class="rounded-circle" alt="profile-photo">
                                            <c:if test="${user.id == profile.user.id}">
                                                <div class="profile-image-action list-group">
                                                    <a href="#" data-bs-toggle="modal"
                                                       data-bs-target="#upload-profile-photo">
                                                        <i class="icon-upload"></i>
                                                        <span><spring:message
                                                                code="profilePage.uploadPhoto"/></span>
                                                    </a>
                                                    <div class="modal fade"
                                                         id="upload-profile-photo"
                                                         tabindex="-1">
                                                        <div class="modal-dialog modal-dialog-centered">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title"
                                                                        id="upload-profile-photo-label">
                                                                        <spring:message
                                                                                code="uploadPage.title"/></h5>
                                                                    <button type="button"
                                                                            class="btn-close"
                                                                            data-bs-dismiss="modal"
                                                                            aria-label="Close"></button>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <form:form
                                                                            action="upload-profile-photo"
                                                                            method="POST"
                                                                            enctype="multipart/form-data">
                                                                        <input type="hidden"
                                                                               name="${_csrf.parameterName}"
                                                                               value="${_csrf.token}"/>
                                                                        <p><spring:message
                                                                                code="uploadPage.text"/></p>
                                                                        <input type="file"
                                                                               class="form-control"
                                                                               id="upload-photo"
                                                                               name="file"/>
                                                                        <button type="submit"
                                                                                class="btn btn-primary btn-sm mt-4">
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
                                            </c:if>
                                        </div>
                                        <div class="mt-4 d-flex flex-column w-100">
                                            <c:if test="${noButton == true}">
                                            </c:if>
                                            <c:if test="${inFriendButton == true}">
                                                <a href="<c:url value="/id${userId}/unfriend" />">
                                                    <button class="btn btn-danger w-100 mb-1">
                                                        <spring:message
                                                                code="profilePage.button.unfriend"/></button>
                                                </a>
                                            </c:if>
                                            <c:if test="${requestButton == true}">
                                                <a href="<c:url value="/id${userId}/cancel-request"/>">
                                                    <button class="btn btn-secondary w-100 mb-1">
                                                        <spring:message
                                                                code="profilePage.button.inRequest"/>
                                                    </button>
                                                </a>
                                            </c:if>
                                            <c:if test="${addButton == true}">
                                                <a href="<c:url value="/id${userId}/add-to-friends" />">
                                                    <button class="btn btn-primary w-100 mb-1">
                                                        <spring:message
                                                                code="profilePage.button.add"/></button>
                                                </a>
                                            </c:if>
                                            <c:if test="${user.id != userId}">
                                                <a href="<c:url value="/messages/${profile.id}"/>">
                                                    <button class="btn btn-primary w-100">
                                                        <spring:message
                                                                code="profilePage.message"/></button>
                                                </a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${friendCount != 0}">
                                <div class="card mt-3">
                                    <div class="row friend-count">
                                        <a href="<c:url value="/id${userId}/friends"/>"
                                           class="text-secondary m-2"><spring:message
                                                code="profilePage.friends"/> (${friendCount})</a>
                                    </div>
                                    <div class="container friend-container">
                                        <div class="row">
                                            <c:forEach items="${friends}" var="friend" begin="0"
                                                       end="3">
                                                <div class="col-3 p-1">
                                                    <div class="friend-row">
                                                        <div>
                                                            <a class="d-flex justify-content-center friend-row-wrap w-auto"
                                                               href="<c:url value="/id${friend.user.id}"/>"><img
                                                                    src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${friend.profilePhotoName}"/>"
                                                                    class="rounded-circle"
                                                                    alt=""/></a>
                                                        </div>
                                                        <div class="d-flex justify-content-center friend-name text-center">
                                                            <a href="<c:url value="/id${friend.user.id}"/>"
                                                               class="text-secondary">${friend.basicInformation.firstname}</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${friendCount == 0 && user.id == userId}">
                                <div class="card mt-3">
                                    <div class="row friend-count">
                                        <a href="<c:url value="/id${userId}/friends"/>"
                                           class="text-secondary m-2"><spring:message
                                                code="profilePage.friends"/> (${friendCount})</a>
                                    </div>
                                    <div class="container friend-container">
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="d-flex justify-content-center profile-no-image">
                                                    <a href="<c:url value="/search"/>"
                                                       class="text-secondary">
                                                        <i class="d-flex icon-plus icon-2x justify-content-center"></i>
                                                        <span class="d-flex justify-content-center">
                                                                    <spring:message
                                                                            code="profilePage.addFriends"/>
                                                                </span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <div class="col-8">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-10">
                                            <div class="profile-name mb-0">
                                                <h4>
                                                    ${profile.basicInformation.firstname} ${profile.basicInformation.lastname}
                                                </h4>
                                            </div>
                                        </div>
                                        <div class="col-2 profile-edit">
                                            <c:if test="${user.id == profile.user.id}">
                                                <a class=""
                                                   href="<c:url value='/edit/profile'/>"><spring:message
                                                        code="profilePage.edit"/></a>
                                            </c:if>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row mb-2">
                                        <div class="col-4">
                                            <h6 class="mb-0">
                                                <span class="text-secondary"><spring:message
                                                        code="profilePage.birthday"/></span>
                                            </h6>
                                        </div>
                                        <div class="col-8">
                                            <span>${profile.basicInformation.birthday}</span>
                                        </div>
                                    </div>
                                    <c:if test="${not empty profile.country}">
                                        <div class="row mb-2">
                                            <div class="col-4">
                                                <h6 class="mb-0">
                                                <span class="text-secondary"><spring:message
                                                        code="profilePage.country"/></span>
                                                </h6>
                                            </div>
                                            <div class="col-8">
                                                <span>${profile.country.name}</span>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty profile.city}">
                                        <div class="row mb-2">
                                            <div class="col-4">
                                                <h6 class="mb-0">
                                                <span class="text-secondary"><spring:message
                                                        code="profilePage.city"/></span></h6>
                                            </div>
                                            <div class="col-8">
                                                <span>${profile.city}</span>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty profile.basicInformation.relationship}">
                                        <div class="row mb-2">
                                            <div class="col-4">
                                                <h6 class="mb-0">
                                                <span class="text-secondary"><spring:message
                                                        code="profilePage.relationship"/></span>
                                                </h6>
                                            </div>
                                            <div class="col-8">
                                                <span>${profile.basicInformation.relationship.name}</span>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty profile.jobTitle}">
                                        <div class="row">
                                            <div class="col-4">
                                                <h6 class="mb-0">
                                                  <span class="text-secondary"><spring:message
                                                          code="profilePage.job"/></span>
                                                </h6>
                                            </div>
                                            <div class="col-8">
                                                <span>${profile.jobTitle}</span>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty profile.mobilePhone || not empty profile.homePhone || not empty profile.githubName || not empty profile.twitterName || not empty profile.instagramName || not empty profile.facebookName || not empty profile.skypeName}">
                                        <button class="btn collapse-contact-info-button mt-3 mb-3"
                                                type="button" data-bs-toggle="collapse"
                                                data-bs-target="#collapse-contact-info"
                                                aria-expanded="false"
                                                aria-controls="collapseExample">
                                            <spring:message
                                                    code="profilePage.collapse.title"/>
                                        </button>
                                        <div class="row mt-2 collapse" id="collapse-contact-info">
                                            <div class="contact-info">
                                                <div class="col-sm-12">
                                                    <div class="contact-info-header mb-3">
                                                    <span><spring:message
                                                            code="profilePage.contact.title"/></span>
                                                    </div>
                                                    <c:if test="${not empty profile.mobilePhone}">
                                                        <div class="row mb-2">
                                                            <div class="col-4">
                                                                <h6 class="mb-0">
                                                                    <span class="text-secondary"><spring:message
                                                                            code="profilePage.mobile"/></span>
                                                                </h6>
                                                            </div>
                                                            <div class="col-8">
                                                                <span>${profile.mobilePhone}</span>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${not empty profile.homePhone}">
                                                        <div class="row mb-2">
                                                            <div class="col-4">
                                                                <h6 class="mb-0">
                                                                    <span class="text-secondary"><spring:message
                                                                            code="profilePage.phone"/></span>
                                                                </h6>
                                                            </div>
                                                            <div class="col-8">
                                                                <span>${profile.homePhone}</span>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${not empty profile.githubName}">
                                                        <div class="row mb-2">
                                                            <div class="col-4">
                                                                <h6 class="mb-0 text-secondary">
                                                                    <i class="icon-github icon-large"></i>
                                                                    <span>Github</span>
                                                                </h6>
                                                            </div>
                                                            <div class="col-8">
                                                                <a href="${profile.githubName}">${profile.githubName}</a>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${not empty profile.twitterName}">
                                                        <div class="row mb-2">
                                                            <div class="col-4">
                                                                <h6 class="mb-0 text-secondary">
                                                                    <i class="icon-twitter icon-large"></i>
                                                                    <span>Twitter</span>
                                                                </h6>
                                                            </div>
                                                            <div class="col-8">
                                                                <a href="${profile.twitterName}">${profile.twitterName}</a>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${not empty profile.instagramName}">
                                                        <div class="row mb-2">
                                                            <div class="col-4">
                                                                <h6 class="mb-0 text-secondary">
                                                                    <i class="icon-instagram icon-large"></i>
                                                                    <span>Instagram</span>
                                                                </h6>
                                                            </div>
                                                            <div class="col-8">
                                                                <a href="${profile.instagramName}">${profile.instagramName}</a>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${not empty profile.facebookName}">
                                                        <div class="row mb-2">
                                                            <div class="col-4">
                                                                <h6 class="mb-0 text-secondary">
                                                                    <i class="icon-facebook icon-large"></i>
                                                                    <span>Facebook</span>
                                                                </h6>
                                                            </div>
                                                            <div class="col-8">
                                                                <a href="${profile.facebookName}">${profile.facebookName}</a>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${not empty profile.skypeName}">
                                                        <div class="row mb-2">
                                                            <div class="col-4">
                                                                <h6 class="mb-0 text-secondary">
                                                                    <i class="icon-skype icon-large"></i>
                                                                    <span>Skype</span>
                                                                </h6>
                                                            </div>
                                                            <div class="col-8">
                                                                <span>${profile.skypeName}</span>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${photoCount != 0}">
                                <div class="card mb-3">
                                    <div class="card-body pt-0">
                                        <div class="row">
                                            <div>
                                                <div class="row d-flex justify-content-center">
                                                    <div class="photo-title m-2"><a
                                                            href="<c:url value="/id${userId}/photos"/>"
                                                            class="text-secondary"><spring:message
                                                            code="profilePage.photos"/>
                                                        (${photoCount})</a>
                                                    </div>
                                                    <c:forEach items="${photos}" var="photo"
                                                               begin="0"
                                                               end="2">
                                                        <div class="col-lg-4 col-md-12 mb-4 mb-lg-0 image-wrap">
                                                            <div>
                                                                <a href="<c:url value="/id${userId}/photos/${photo.id}"/>"><img
                                                                        src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${photo.name}"/>"
                                                                        class="w-100"/></a>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${photoCount == 0 && user.id == userId}">
                                <div class="card mb-3">
                                    <div class="card-body pt-0">
                                        <div class="row">
                                            <div>
                                                <div class="row d-flex justify-content-center">
                                                    <div class="photo-title m-2"><a
                                                            href="<c:url value="/id${userId}/photos"/>"
                                                            class="text-secondary"><spring:message
                                                            code="profilePage.photos"/>
                                                        (${photoCount})</a>
                                                    </div>
                                                    <div class="col-12">
                                                        <div class="d-flex justify-content-center profile-no-image">
                                                            <a href="<c:url value="/id${user.id}/photos"/>"
                                                               class="text-secondary">
                                                                <i class="icon-picture icon-3x d-block d-flex justify-content-center"></i>
                                                                <span class="d-block d-flex justify-content-center">
                                                                    <spring:message
                                                                            code="profilePage.addPhotos"/>
                                                                </span>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${error != null}">
                <div class="alert text-white alert-notification-error align-middle w-auto"
                     role="alert">
                        ${error}
                </div>
            </c:if>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>
