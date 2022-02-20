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
                <div class="row">
                    <div class="col-12">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-9">
                                        <h4 class="mb-0 align-content-center"><span><spring:message
                                                code="photoPage.title"/></span></h4>
                                    </div>
                                    <c:if test="${user.id == userId}">
                                        <div class="col-3">

                                            <button type="button" data-bs-target="#upload-photo"
                                                    data-bs-toggle="modal"
                                                    class="btn btn-primary btn-sm d-block mx-auto mb-3">
                                            <span class="p-1"><spring:message
                                                    code="button.addPhoto"/></span>
                                            </button>

                                            <div class="modal fade" id="upload-photo" tabindex="-1">
                                                <div class="modal-dialog modal-dialog-centered">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title"
                                                                id="upload-photo-label">
                                                                <spring:message
                                                                        code="uploadPage.title"/></h5>
                                                            <button type="button" class="btn-close"
                                                                    data-bs-dismiss="modal"
                                                                    aria-label="Close"></button>
                                                        </div>
                                                        <c:if test="${messageError != null}">
                                                            <div class="alert alert-danger"
                                                                 role="alert">
                                                                    ${messageError}
                                                            </div>
                                                        </c:if>
                                                        <div class="modal-body">
                                                            <form:form action="photos/upload-photo"
                                                                       id="upload-photo"
                                                                       method="POST"
                                                                       enctype="multipart/form-data">
                                                                <input type="hidden"
                                                                       name="${_csrf.parameterName}"
                                                                       value="${_csrf.token}"/>
                                                                <p><spring:message
                                                                        code="uploadPage.text"/></p>
                                                                <input type="file"
                                                                       class="form-control"
                                                                       id=photo" name="file"/>
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
                                        </div>
                                    </c:if>
                                    <hr class="mt-2">
                                    <div class="container-fluid">
                                        <div class="row photos-wrapper">
                                            <c:if test="${message != null}">
                                                <div class="col-12 d-flex justify-content-center align-content-center">
                                                    <p class="text-secondary m-0 p-5">${message}</p>
                                                </div>
                                            </c:if>
                                            <c:forEach items="${photos}" var="photo">
                                                <div class="col-12 col-6 col-lg-3 p-1">
                                                    <a href="<c:url value="/id${userId}/photos/${photo.id}"/>">
                                                        <img src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${photo.name}"/>"
                                                             class="d-block w-100">
                                                    </a>
                                                </div>
                                            </c:forEach>
                                            <c:if test="${not empty photos}">
                                                <nav class="d-flex justify-content-center mt-auto mt-1">
                                                    <div>
                                                        <div class="d-flex justify-content-center">
                                                            <ul class="pagination">
                                                                <li class="page-item disabled">
                                                                    <a href="#" class="page-link"
                                                                       tabindex="-1">
                                                                        <spring:message
                                                                                code="pages"/>
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
                                                                                    href="<c:url value="/id${userId}/photos?page=${pageNumber-1}&size=${size}"/>">${pageNumber}</a>
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
                                                                                    href="<c:url value="/id${userId}/photos?page=${page}&size=${element}"/>">${element}</a>
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
        </div>
    </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>
