<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="by.sam_solutions.kazak.social_network.entities.Gender" %>
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
            <div class="col-8 mb-4">
                <div class="col-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12">
                                    <h4><span><spring:message
                                            code="adminProfileEditPage.title"/></span></h4>
                                </div>
                            </div>
                            <hr>
                            <ul class="nav nav-tabs mb-3" id="myTab" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active" id="profile-tab"
                                            data-bs-toggle="tab"
                                            data-bs-target="#profile" type="button" role="tab"
                                            aria-controls="profile"
                                            aria-selected="true"><spring:message
                                            code="adminProfileEditPage.tab.profile"/>
                                    </button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="basic-tab" data-bs-toggle="tab"
                                            data-bs-target="#basic" type="button" role="tab"
                                            aria-controls="basic" aria-selected="false">
                                        <spring:message
                                                code="adminProfileEditPage.tab.basicInfo"/>
                                    </button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="security-tab" data-bs-toggle="tab"
                                            data-bs-target="#security" type="button" role="tab"
                                            aria-controls="security" aria-selected="false">
                                        <spring:message
                                                code="adminProfileEditPage.tab.security"/>
                                    </button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="role-tab" data-bs-toggle="tab"
                                            data-bs-target="#role" type="button" role="tab"
                                            aria-controls="block" aria-selected="false">
                                        <spring:message
                                                code="adminProfileEditPage.tab.role"/>
                                    </button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="lock-tab" data-bs-toggle="tab"
                                            data-bs-target="#lock" type="button" role="tab"
                                            aria-controls="lock" aria-selected="false">
                                        <spring:message
                                                code="adminProfileEditPage.tab.locking"/>
                                    </button>
                                </li>
                            </ul>
                            <div class="tab-content" id="myTabContent">
                                <div class="container tab-pane fade show active" id="profile"
                                     role="tabpanel"
                                     aria-labelledby="profile-tab">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="card-body">
                                                <c:if test="${profileErrors != null}">
                                                    <c:forEach var="error"
                                                               items="${profileErrors.allErrors}">
                                                        <div class="alert alert-danger"
                                                             role="alert">
                                                            <spring:message message="${error}"/>
                                                        </div>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${profileMessageSuccess != null}">
                                                    <div class="alert alert-success" role="alert">
                                                            ${profileMessageSuccess}
                                                    </div>
                                                </c:if>
                                                <form:form action="edit/profile" id="profile-form"
                                                           method="POST"
                                                           modelAttribute="contactInformationDTO">
                                                    <form:input path="id" type="hidden" id="id"
                                                                class="form-control"
                                                                value="${profile.id}"/>
                                                    <div class="row mb-3 mx-auto">
                                                        <div class="col-3 d-flex align-items-center justify-content-end">
                                                            <h6 class="mb-0 text-secondary">
                                                                <span><spring:message
                                                                        code="adminProfileEditPage.tab.profile.country"/>
                                                                </span>
                                                            </h6>
                                                        </div>
                                                        <div class="col-8 form-outline">
                                                            <form:select path="countryId"
                                                                         class="form-select form-select">
                                                                <c:forEach items="${countries}"
                                                                           var="country">
                                                                    <option name="${country.id}"
                                                                            value="${country.id}"
                                                                            label="${country.name}"
                                                                            <c:if test="${profile.country.id == country.id}"> selected </c:if>>
                                                                    </option>
                                                                </c:forEach>
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                    <div class="row mb-3 mx-auto">
                                                        <div class="col-3 d-flex align-items-center justify-content-end">
                                                            <h6 class="mb-0 text-secondary">
                                                                <span><spring:message
                                                                        code="adminProfileEditPage.tab.profile.city"/></span>
                                                            </h6>
                                                        </div>
                                                        <div class="col-8 form-outline">
                                                            <form:input path="city" type="text"
                                                                        maxlength="255"
                                                                        class="form-control"
                                                                        aria-label="city"
                                                                        id="city"
                                                                        value="${profile.city}"/>
                                                        </div>
                                                    </div>
                                                    <div class="row mb-3 mx-auto">
                                                        <div class="col-3 d-flex align-items-center justify-content-end">
                                                            <h6 class="mb-0 text-secondary">
                                                                <span><spring:message
                                                                        code="adminProfileEditPage.tab.profile.job"/></span>
                                                            </h6>
                                                        </div>
                                                        <div class="col-8 form-outline">
                                                            <form:input path="jobTitle" type="text"
                                                                        maxlength="45"
                                                                        class="form-control"
                                                                        aria-label="jobTitle"
                                                                        id="jobTitle"
                                                                        value="${profile.jobTitle}"/>
                                                        </div>
                                                    </div>
                                                    <hr>
                                                    <div class="row mb-3 mx-auto">
                                                        <div class="col-3 d-flex align-items-center justify-content-end">
                                                            <h6 class="mb-0 text-secondary">
                                                                <span><spring:message
                                                                        code="adminProfileEditPage.tab.profile.mobile"/></span>
                                                            </h6>
                                                        </div>
                                                        <div class="col-8 form-outline">
                                                            <form:input path="mobilePhone"
                                                                        maxlength="45"
                                                                        type="text"
                                                                        class="form-control"
                                                                        aria-label="mobilePhone"
                                                                        id="mobilePhone"
                                                                        value="${profile.mobilePhone}"/>
                                                        </div>
                                                    </div>
                                                    <div class="row mb-3 mx-auto">
                                                        <div class="col-3 d-flex align-items-center justify-content-end">
                                                            <h6 class="mb-0 text-secondary">
                                                                <span><spring:message
                                                                        code="adminProfileEditPage.tab.profile.homePhone"/></span>
                                                            </h6>
                                                        </div>
                                                        <div class="col-8 form-outline">
                                                            <form:input path="homePhone" type="text"
                                                                        maxlength="45"
                                                                        class="form-control"
                                                                        aria-label="homePhone"
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
                                                            <form:input path="githubName"
                                                                        type="text" maxlength="45"
                                                                        class="form-control"
                                                                        aria-label="githubName"
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
                                                            <form:input path="twitterName"
                                                                        type="text" maxlength="45"
                                                                        class="form-control"
                                                                        aria-label="twitterName"
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
                                                            <form:input path="instagramName"
                                                                        type="text" maxlength="45"
                                                                        class="form-control"
                                                                        aria-label="instagramName"
                                                                        id="instagram"
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
                                                            <form:input path="facebookName"
                                                                        type="text" maxlength="45"
                                                                        class="form-control"
                                                                        aria-label="facebookName"
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
                                                            <form:input path="skypeName" type="text"
                                                                        maxlength="45"
                                                                        class="form-control"
                                                                        aria-label="skypeName"
                                                                        id="skype"
                                                                        value="${profile.skypeName}"/>
                                                        </div>
                                                    </div>
                                                    <hr>
                                                    <button type="submit"
                                                            class="btn btn-primary btn-sm d-block mx-auto">
                                <span class="p-4"><spring:message
                                        code="button.save"/></span>
                                                    </button>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="container tab-pane fade" id="basic" role="tabpanel"
                                     aria-labelledby="basic-tab">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="card-body">
                                                <c:if test="${basicErrors != null}">
                                                    <c:forEach var="error"
                                                               items="${basicErrors.allErrors}">
                                                        <div class="alert alert-danger"
                                                             role="alert">
                                                            <spring:message message="${error}"/>
                                                        </div>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${basicMessageSuccess != null}">
                                                    <div class="alert alert-success" role="alert">
                                                            ${basicMessageSuccess}
                                                    </div>
                                                </c:if>
                                                <form:form action="edit/basic" id="basic-form"
                                                           method="POST"
                                                           modelAttribute="basicInformationDTO">
                                                    <div class="row mb-3 mx-auto">
                                                        <form:input path="id" type="hidden" id="id"
                                                                    class="form-control"
                                                                    value="${profile.basicInformation.id}"/>
                                                        <div class="col-6">
                                                            <label for="firstname"
                                                                   class="text-secondary"><spring:message
                                                                    code="adminProfileEditPage.tab.basicInfo.firstname"/></label>
                                                            <form:input path="firstname" type="text"
                                                                        maxlength="255"
                                                                        id="firstname"
                                                                        class="form-control"
                                                                        aria-label="firstname"
                                                                        value="${profile.basicInformation.firstname}"/>
                                                        </div>
                                                        <div class="col-6">
                                                            <label for="lastname"
                                                                   class="text-secondary"><spring:message
                                                                    code="adminProfileEditPage.tab.basicInfo.lastname"/></label>
                                                            <form:input path="lastname" type="text"
                                                                        maxlength="255"
                                                                        id="lastname"
                                                                        class="form-control"
                                                                        aria-label="lastname"
                                                                        value="${profile.basicInformation.lastname}"/>
                                                        </div>
                                                    </div>
                                                    <div class="row mb-3 mx-auto">
                                                        <div class="col-6">
                                                            <label for="gender"
                                                                   class="text-secondary"><spring:message
                                                                    code="adminProfileEditPage.tab.basicInfo.gender"/></label>
                                                            <form:select path="gender"
                                                                         class="form-select form-select"
                                                                         id="gender">
                                                                <option name="${Gender.NOT_KNOWN}"
                                                                        value="${Gender.NOT_KNOWN}"
                                                                        <c:if
                                                                                test="${profile.basicInformation.gender == Gender.NOT_KNOWN.name()}"> selected </c:if>>
                                                                    Not known
                                                                </option>
                                                                <option name="${Gender.MALE}"
                                                                        value="${Gender.MALE}" <c:if
                                                                        test="${profile.basicInformation.gender == Gender.MALE.name()}"> selected </c:if>>
                                                                    Male
                                                                </option>
                                                                <option name="${Gender.FEMALE}"
                                                                        value="${Gender.FEMALE}"
                                                                        <c:if
                                                                                test="${profile.basicInformation.gender == Gender.FEMALE.name()}"> selected </c:if>>
                                                                    Female
                                                                </option>
                                                                <option name="${Gender.NOT_APPLICABLE}"
                                                                        value="${Gender.NOT_APPLICABLE}"
                                                                        <c:if
                                                                                test="${profile.basicInformation.gender == Gender.NOT_APPLICABLE.name()}"> selected </c:if>>
                                                                    Not applicable
                                                                </option>
                                                            </form:select>
                                                        </div>
                                                        <div class="col-6">
                                                            <label for="birthday"
                                                                   class="text-secondary"><spring:message
                                                                    code="adminProfileEditPage.tab.basicInfo.birthday"/></label>
                                                            <form:input path="birthday" type="date"
                                                                        pattern="\d{4}-\d{2}-\d{2}"
                                                                        aria-label="birthday"
                                                                        class="form-control p-1"
                                                                        id="birthday"
                                                                        value="${profile.basicInformation.birthday}"/>
                                                        </div>
                                                    </div>
                                                    <div class="row mb-3 mx-auto">
                                                        <div class="col-6">
                                                            <label for="relationship"
                                                                   class="text-secondary"><spring:message
                                                                    code="adminProfileEditPage.tab.basicInfo.relationship"/></label>
                                                            <form:select path="relationshipId"
                                                                         class="form-select form-select"
                                                                         id="relationship">
                                                                <c:forEach items="${relationships}"
                                                                           var="relationship">
                                                                    <option name="${relationship.id}"
                                                                            value="${relationship.id}"
                                                                            label="${relationship.name}"
                                                                            <c:if test="${profile.basicInformation.relationship.id == relationship.id}">selected</c:if>>
                                                                    </option>
                                                                </c:forEach>
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                    <hr>
                                                    <button type="submit"
                                                            class="btn btn-primary btn-sm d-block mx-auto">
                                                                <span class="p-4">
                                                                    <spring:message
                                                                            code="button.save"/>
                                                                 </span>
                                                    </button>
                                                </form:form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="container tab-pane fade" id="security" role="tabpanel"
                                     aria-labelledby="security-tab">
                                    <div class="row">
                                        <div class="card mb-4">
                                            <div class="card-body">
                                                <div class="col-12">
                                                    <c:if test="${emailMessageError != null}">
                                                        <div class="alert alert-danger"
                                                             role="alert">
                                                                ${emailMessageError}
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${emailMessageSuccess != null}">
                                                        <div class="alert alert-success"
                                                             role="alert">
                                                                ${emailMessageSuccess}
                                                        </div>
                                                    </c:if>
                                                    <form:form action="edit/email-change"
                                                               method="POST"
                                                               id="email-change">
                                                        <div class="row mb-3 mx-auto justify-content-center text-center">
                                                            <p class="text-secondary">
                                                                <spring:message
                                                                        code="adminProfileEditPage.tab.security.currentEmail"/>: ${profile.user.email}</p>
                                                            <div class="col-6">
                                                                <input type="text" id="email" maxlength="255"
                                                                       class="form-control" aria-label="email"
                                                                       name="email"
                                                                       placeholder="<spring:message code="adminProfileEditPage.tab.security.placeholder.newEmail"/>"/>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <button type="submit"
                                                                class="btn btn-primary btn-sm d-block mx-auto">
                                                            <span class="p-4"><spring:message
                                                                    code="button.save"/></span>
                                                        </button>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="card mb-4">
                                            <div class="card-body">
                                                <div class="col-12">
                                                    <c:if test="${passwordMessageError != null}">
                                                        <div class="alert alert-danger"
                                                             role="alert">
                                                                ${passwordMessageError}
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${passwordMessageSuccess != null}">
                                                        <div class="alert alert-success"
                                                             role="alert">
                                                                ${passwordMessageSuccess}
                                                        </div>
                                                    </c:if>
                                                    <form:form action="edit/password-change"
                                                               method="POST"
                                                               id="password-change">
                                                        <div class="row mb-3 mx-auto justify-content-center text-center">
                                                            <p class="text-secondary">
                                                                <spring:message
                                                                        code="adminProfileEditPage.tab.security.createPassword"/></p>
                                                            <div class="col-8">
                                                                <input type="password" maxlength="20"
                                                                       id="new-password"
                                                                       class="form-control" aria-label="password"
                                                                       name="newPassword"
                                                                       placeholder="<spring:message code="adminProfileEditPage.tab.security.placeholder.newPassword"/>"/>
                                                            </div>
                                                        </div>
                                                        <div class="row mb-3 mx-auto justify-content-center text-center">
                                                            <div class="col-8">
                                                                <input type="password" maxlength="20"
                                                                       id="re-password"
                                                                       class="form-control" aria-label="re-password"
                                                                       name="confirmPassword"
                                                                       placeholder="<spring:message code="adminProfileEditPage.tab.security.placeholder.confirmPassword"/>"/>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                        <button type="submit"
                                                                class="btn btn-primary btn-sm d-block mx-auto">
                                                            <span class="p-4"><spring:message
                                                                    code="button.save"/></span>
                                                        </button>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="container tab-pane fade" id="role" role="tabpanel"
                                     aria-labelledby="role-tab">
                                    <div class="row">
                                        <div class="col-12 form-outline">
                                            <c:if test="${roleMessageSuccess != null}">
                                                <div class="alert alert-success"
                                                     role="alert">
                                                        ${roleMessageSuccess}
                                                </div>
                                            </c:if>
                                            <form:form action="edit/role-change" method="POST"
                                                       id="role-change">
                                                <select class="form-select form-select" name="role"
                                                        id="roles">
                                                    <c:forEach items="${roles}"
                                                               var="role">
                                                        <option name="${role.id}"
                                                                value="${role.id}"
                                                                label="${role.name}"
                                                                <c:if test="${profile.user.role.id == role.id}">selected</c:if>>
                                                        </option>
                                                    </c:forEach>
                                                </select>
                                                <hr>
                                                <button type="submit"
                                                        class="btn btn-primary btn-sm d-block mx-auto">
                                                    <span class="p-4">Save</span>
                                                </button>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                                <div class="container tab-pane fade" id="lock" role="tabpanel"
                                     aria-labelledby="block-tab">
                                    <div class="row">
                                        <div class="col-12 form-outline">
                                            <c:if test="${lockMessageSuccess != null}">
                                                <div class="alert alert-success"
                                                     role="alert">
                                                        ${lockMessageSuccess}
                                                </div>
                                            </c:if>
                                            <form:form action="edit/lock-change" method="POST"
                                                       id="lock-change">
                                                <select class="form-select form-select"
                                                        name="isLocked"
                                                        id="roles">
                                                    <option name="isLocked"
                                                            value="false"
                                                            label="<spring:message code="adminProfileEditPage.tab.lock.isLocked.no"/>"
                                                            <c:if test="${profile.user.locked == false}">selected</c:if>>
                                                    </option>
                                                    <option name="isLocked"
                                                            value="true"
                                                            label="<spring:message code="adminProfileEditPage.tab.lock.isLocked.yes"/>"
                                                            <c:if test="${profile.user.locked == true}">selected</c:if>>
                                                    </option>
                                                </select>
                                                <hr>
                                                <button type="submit"
                                                        class="btn btn-primary btn-sm d-block mx-auto">
                                                    <span class="p-4">Save</span>
                                                </button>
                                            </form:form>
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
