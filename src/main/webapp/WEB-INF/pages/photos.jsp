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
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-9">
                                        <h4 class="mb-0 align-content-center"><span><spring:message
                                                code="photoPage.title"/></span></h4>
                                    </div>
                                    <div class="col-sm-3">
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
                                                            <input type="file" class="form-control"
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
                                    <hr>
                                    <div class="container-fluid">
                                        <div class="row">
                                            <c:if test="${message != null}">
                                                <div class="col-12 d-flex justify-content-center align-content-center ">
                                                    <p class="text-secondary m-0 p-5">${message}</p>
                                                </div>
                                            </c:if>
                                            <c:forEach items="${photos}" var="photo">
                                                <div class="col-12 col-sm-6 col-lg-3 p-1">
                                                    <a href="<c:url value="/id${profileId}/photos/${photo.id}"/>">
                                                        <img src="<c:url value="https://social-network-sam.s3.eu-north-1.amazonaws.com/${photo.name}"/>"
                                                             class="d-block w-100">
                                                    </a>
                                                </div>
                                            </c:forEach>
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
