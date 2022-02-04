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
            <div class="col-6">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-8">
                                <h4 class="mb-0"><span><spring:message
                                        code="profileEditPage.title"/></span></h4>
                            </div>
                        </div>
                        <hr>
                        <c:if test="${errors != null}">
                            <c:forEach var="error"
                                       items="${errors.allErrors}">
                                <div class="alert alert-danger"
                                     role="alert">
                                    <spring:message message="${error}"/>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${messageSuccess != null}">
                            <div class="alert alert-success" role="alert">
                                    ${messageSuccess}
                            </div>
                        </c:if>
                        <form:form action="profile" id="edit-form" method="POST"
                                   modelAttribute="contactInformationDTO">
                            <form:input path="id" type="hidden" id="id"
                                        class="form-control"
                                        value="${profile.id}"/>
                            <div class="row mb-3 mx-auto">
                                <div class="col-3 d-flex align-items-center justify-content-end">
                                    <h6 class="mb-0 text-secondary">
                                        <span><spring:message
                                                code="profileEditPage.country"/></span>
                                    </h6>
                                </div>
                                <div class="col-8 form-outline">
                                    <form:select path="countryId" class="form-select form-select">
                                        <c:forEach items="${countries}" var="country">
                                            <option name="${country.id}" value="${country.id}"
                                                    label="${country.name}"
                                                    <c:if test="${profile.country == country}"> selected </c:if>>
                                            </option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto">
                                <div class="col-3 d-flex align-items-center justify-content-end">
                                    <h6 class="mb-0 text-secondary">
                                        <span><spring:message code="profileEditPage.city"/></span>
                                    </h6>
                                </div>
                                <div class="col-8 form-outline">
                                    <form:input path="city" type="text" class="form-control"
                                                maxlength="500" aria-label="city"
                                                id="city"
                                                value="${profile.city}"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto">
                                <div class="col-3 d-flex align-items-center justify-content-end">
                                    <h6 class="mb-0 text-secondary">
                                        <span><spring:message code="profileEditPage.job"/></span>
                                    </h6>
                                </div>
                                <div class="col-8 form-outline">
                                    <form:input path="jobTitle" type="text" class="form-control"
                                                maxlength="45" aria-label="jobTitle"
                                                id="jobTitle"
                                                value="${profile.jobTitle}"/>
                                </div>
                            </div>
                            <hr>
                            <div class="row mb-3 mx-auto">
                                <div class="col-3 d-flex align-items-center justify-content-end">
                                    <h6 class="mb-0 text-secondary">
                                        <span><spring:message code="profileEditPage.mobile"/></span>
                                    </h6>
                                </div>
                                <div class="col-8 form-outline">
                                    <form:input path="mobilePhone" type="text" class="form-control"
                                                maxlength="45" aria-label="mobilePhone"
                                                id="mobilePhone"
                                                value="${profile.mobilePhone}"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto">
                                <div class="col-3 d-flex align-items-center justify-content-end">
                                    <h6 class="mb-0 text-secondary">
                                        <span><spring:message
                                                code="profileEditPage.homePhone"/></span>
                                    </h6>
                                </div>
                                <div class="col-8 form-outline">
                                    <form:input path="homePhone" type="text" class="form-control"
                                                maxlength="45" aria-label="homePhone"
                                                id="homePhone"
                                                value="${profile.homePhone}"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto">
                                <div class="col-3 d-flex align-items-center justify-content-end">
                                    <h6 class="mb-0 text-secondary">
                                        <span>GitHub</span>
                                    </h6>
                                </div>
                                <div class="col-8 form-outline">
                                    <form:input path="githubName" type="text" class="form-control"
                                                maxlength="45" aria-label="githubName"
                                                id="github"
                                                value="${profile.githubName}"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto">
                                <div class="col-3 d-flex align-items-center justify-content-end">
                                    <h6 class="mb-0 text-secondary">
                                        <span>Twitter</span>
                                    </h6>
                                </div>
                                <div class="col-8 form-outline">
                                    <form:input path="twitterName" type="text" class="form-control"
                                                maxlength="45" aria-label="twitterName"
                                                id="twitter"
                                                value="${profile.twitterName}"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto">
                                <div class="col-3 d-flex align-items-center justify-content-end">
                                    <h6 class="mb-0 text-secondary">
                                        <span>Instagram</span>
                                    </h6>
                                </div>
                                <div class="col-8 form-outline">
                                    <form:input path="instagramName" type="text" maxlength="45"
                                                aria-label="instagramName"
                                                class="form-control" id="instagram"
                                                value="${profile.instagramName}"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto">
                                <div class="col-3 d-flex align-items-center justify-content-end">
                                    <h6 class="mb-0 text-secondary">
                                        <span>Facebook</span>
                                    </h6>
                                </div>
                                <div class="col-8 form-outline">
                                    <form:input path="facebookName" type="text" class="form-control"
                                                maxlength="45" aria-label="facebookName"
                                                id="facebook"
                                                value="${profile.facebookName}"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto">
                                <div class="col-3 d-flex align-items-center justify-content-end">
                                    <h6 class="mb-0 text-secondary">
                                        <span>Skype</span>
                                    </h6>
                                </div>
                                <div class="col-8 form-outline">
                                    <form:input path="skypeName" type="text" class="form-control"
                                                maxlength="45" aria-label="skypeName"
                                                id="skype"
                                                value="${profile.skypeName}"/>
                                </div>
                            </div>
                            <hr>
                            <button type="submit" class="btn btn-primary btn-sm d-block mx-auto">
                                <span class="p-4"><spring:message
                                        code="button.save"/></span>
                            </button>
                        </form:form>
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
