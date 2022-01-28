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

<jsp:include page="header.jsp"/>

<section>
    <div class="container">
        <div class="row">

            <jsp:include page="side-nav.jsp"/>

            <div class="col-sm-8 mb-4">
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="post">
                                <div class="post-heading mb-4">
                                    <div class="pull-left">
                                        <a href="<c:url value="/id${profile.user.id}"/>">
                                            <img src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${profile.profilePhotoName}"/>"
                                                 class="rounded-circle profile-photo">
                                        </a>
                                    </div>
                                    <div class="pull-left meta">
                                        <div class="title h6">
                                            <a href="<c:url value="/id${profile.user.id}"/>"
                                               class="text-black">
                                                <b>${profile.basicInformation.firstname} ${profile.basicInformation.lastname}</b>
                                            </a>
                                        </div>
                                        <h6 class="text-secondary time">${photo.timeCreation.toLocalDate()} ${photo.timeCreation.toLocalTime()}</h6>
                                    </div>
                                    <div class="d-flex justify-content-end">
                                        <a class="nav-link nav-profile" href="#" id="photoDropdown"
                                           role="button"
                                           data-bs-toggle="dropdown" data-display="static"
                                           aria-expanded="false">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24"
                                                 height="24" viewBox="0 0 24 24" fill="none"
                                                 stroke="currentColor" stroke-width="2"
                                                 stroke-linecap="round" stroke-linejoin="round"
                                                 class="feather feather-more-horizontal align-middle">
                                                <circle cx="12" cy="12" r="1"></circle>
                                                <circle cx="19" cy="12" r="1"></circle>
                                                <circle cx="5" cy="12" r="1"></circle>
                                            </svg>
                                        </a>
                                        <ul class="dropdown-menu dropdown-menu-end"
                                            aria-labelledby="photoDropdown">
                                            <li><a href="#" class="dropdown-item"
                                                   data-bs-toggle="modal"
                                                   data-bs-target="#description"><spring:message
                                                    code="photoPage.addDescription"/></a>
                                            </li>
                                            <li><a class="dropdown-item"
                                                   href="<c:url value="/id${profile.user.id}/photos/${photo.id}/delete"/>"><spring:message
                                                    code="photoPage.deletePhoto"/></a>
                                            </li>
                                        </ul>
                                        <div class="modal fade" id="description" tabindex="-1">
                                            <div class="modal-dialog modal-dialog-centered">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title"
                                                            id="description-label">
                                                            <spring:message
                                                                    code="photoPage.description.title"/></h5>
                                                        <button type="button" class="btn-close"
                                                                data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <form:form method="POST"
                                                                   action="${photo.id}/add-description">
                                                            <div>
                                                                <textarea class="form-control"
                                                                          rows="3"
                                                                          name="description"
                                                                          id="area-description">${photo.description}</textarea>
                                                            </div>
                                                            <button type="submit"
                                                                    class="btn btn-primary btn-sm mt-4">
                                                                <span class="p-4"><spring:message
                                                                        code="button.save"/></span>
                                                            </button>
                                                        </form:form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <hr class="me-3 ms-3">
                                <div class="post-image d-flex justify-content-center">
                                    <img src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${photo.name}"/>"
                                         class="image w-50" alt="image post">
                                </div>
                                <hr>
                                <div class="post-description">
                                    <p>${photo.description}</p>
                                </div>
                                <div class="post-footer">
                                    <form:form method="POST"
                                               action="${photo.id}/add-comment">
                                        <div class="input-group">
                                            <input type="text" class="form-control" name="text"
                                                   placeholder="Add a comment">
                                            <span class="input-group-addon">
                                            <a href="#"><i class="fa fa-edit"></i></a>
                                        </span>
                                        </div>
                                    </form:form>
                                    <c:if test="${message != null}">
                                        <div class="col-12 d-flex justify-content-center align-content-center ">
                                            <p class="text-secondary m-0 p-5">${message}</p>
                                        </div>
                                    </c:if>
                                    <ul class="comments-list">
                                        <c:forEach items="${comments}" var="comment">
                                            <li class="comment">
                                                <a class="pull-left"
                                                   href="<c:url value="/id${comment.profile.user.id}"/>">
                                                    <img class="profile-photo rounded-circle"
                                                         src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${comment.profile.profilePhotoName}"/>"
                                                         alt="avatar">
                                                </a>
                                                <div class="comment-body">
                                                    <div class="comment-heading">
                                                        <a href="<c:url value="/id${comment.profile.user.id}"/>">
                                                            <h4 class="user text-black">${comment.profile.basicInformation.firstname} ${comment.profile.basicInformation.lastname}</h4>
                                                        </a>
                                                        <h5 class="time text-secondary">${comment.timeCreation.toLocalDate()} ${comment.timeCreation.toLocalTime()}</h5>
                                                    </div>
                                                    <p>${comment.text}</p>
                                                    <form:form action="${photo.id}/delete-comment"
                                                               method="POST">
                                                        <input type="hidden" name="commentId"
                                                               value="${comment.id}">
                                                        <button type="submit"
                                                                class="delete-link btn btn-link text-danger">
                                                            <spring:message
                                                                    code="photoPage.deleteComment"/>
                                                        </button>
                                                    </form:form>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
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
