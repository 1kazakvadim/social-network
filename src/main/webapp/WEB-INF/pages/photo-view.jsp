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

<sec:authorize var="isAdmin" access="hasRole('ADMIN')"/>
<sec:authentication var="user" property="principal"/>
<jsp:include page="header.jsp"/>

<section>
    <div class="container">
        <div class="row">

            <jsp:include page="side-nav.jsp"/>

            <div class="col-8 mb-4">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="post">
                                <div class="post-heading mb-4">
                                    <div class="pull-left">
                                        <a class="post-heading-wrap d-flex"
                                           href="<c:url value="/id${profile.user.id}"/>">
                                            <img src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${profile.profilePhotoName}"/>"
                                                 class="rounded-circle">
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
                                    <c:if test="${user.id == profile.user.id}">
                                        <div class="d-flex justify-content-end">
                                            <a class="nav-link nav-profile" href="#"
                                               id="photoDropdown"
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
                                                                          placeholder="<spring:message code="photoView.placeholder.addDescription"/>"
                                                                          rows="3" maxlength="255"
                                                                          aria-label="photo-description"
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
                                    </c:if>
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
                                                   maxlength="255" aria-label="comment-text"
                                                   placeholder="<spring:message code="photoView.placeholder.addComment"/>">
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
                                    <ul class="comments-list mb-2">
                                        <c:forEach items="${comments}" var="comment">
                                            <li class="comment">
                                                <a class="pull-left comment-wrap d-flex pe-1"
                                                   href="<c:url value="/id${comment.profile.user.id}"/>">
                                                    <img class="rounded-circle"
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
                                                    <c:if test="${isAdmin || user.id == comment.profile.user.id}">
                                                        <form:form
                                                                action="${photo.id}/delete-comment"
                                                                method="POST">
                                                            <input type="hidden"
                                                                   name="commentId"
                                                                   value="${comment.id}">
                                                            <button type="submit"
                                                                    class="delete-link btn btn-link text-danger p-0">
                                                                <spring:message
                                                                        code="photoPage.deleteComment"/>
                                                            </button>
                                                        </form:form>
                                                    </c:if>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <c:if test="${not empty comments}">
                                        <nav class="d-flex justify-content-center mt-auto">
                                            <div>
                                                <div class="d-flex justify-content-center">
                                                    <ul class="pagination">
                                                        <li class="page-item disabled">
                                                            <a href="#" class="page-link"
                                                               tabindex="-1">
                                                                <spring:message code="pages"/>
                                                            </a>
                                                        </li>
                                                        <c:forEach begin="1"
                                                                   end="${total / size + 1}"
                                                                   var="pageNumber">
                                                            <c:choose>
                                                                <c:when test="${page == pageNumber - 1}">
                                                                    <li class="page-item active">
                                                                        <a
                                                                                class="page-link"
                                                                                href="#">${pageNumber}</a>
                                                                    </li>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <li class="page-item"><a
                                                                            class="page-link"
                                                                            tabindex="-1"
                                                                            href="<c:url value="/id${userId}/photos/${photoId}?page=${pageNumber-1}&size=${size}"/>">${pageNumber}</a>
                                                                    </li>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                                <div class="d-flex justify-content-center">
                                                    <ul class="pagination">
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
                                                                            href="<c:url value="/id${userId}/photos/${photoId}?page=${page}&size=${element}"/>">${element}</a>
                                                                    </li>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </ul>
                                                </div>
                                            </div>
                                        </nav>
                                    </c:if>
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
