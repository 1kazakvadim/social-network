<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <div class="col-sm-6">
                <div class="card mb-3">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-8">
                                <h4 class="mb-0"><span><spring:message
                                        code="basicInformationEditPage.title"/></span></h4>
                            </div>
                        </div>
                        <hr>
                        <spring:hasBindErrors name="basicInformationDTO">
                            <c:forEach var="error" items="${errors.allErrors}">
                                <div class="alert alert-danger" role="alert">
                                    <spring:message message="${error}"/>
                                </div>
                            </c:forEach>
                        </spring:hasBindErrors>
                        <c:if test="${messageSuccess != null}">
                            <div class="alert alert-success" role="alert">
                                    ${messageSuccess}
                            </div>
                        </c:if>
                        <form:form action="basic" id="edit-basic-form" method="POST"
                                   modelAttribute="basicInformationDTO">
                            <div class="row mb-3 mx-auto">
                                <form:input path="id" type="hidden" id="id"
                                            class="form-control"
                                            value="${profile.basicInformation.id}"/>
                                <div class="col-sm-6">
                                    <label for="firstname" class="text-secondary"><spring:message
                                            code="basicInformationEditPage.firstname"/></label>
                                    <form:input path="firstname" type="text" id="firstname"
                                                class="form-control"
                                                value="${profile.basicInformation.firstname}"/>
                                </div>
                                <div class="col-sm-6">
                                    <label for="lastname" class="text-secondary"><spring:message
                                            code="basicInformationEditPage.lastname"/></label>
                                    <form:input path="lastname" type="text" id="lastname"
                                                class="form-control"
                                                value="${profile.basicInformation.lastname}"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto">
                                <div class="col-sm-6">
                                    <label for="gender" class="text-secondary"><spring:message
                                            code="basicInformationEditPage.gender"/></label>
                                    <form:select path="gender" class="form-select form-select"
                                                 id="gender">
                                        <option name="${Gender.MALE}" value="${Gender.MALE}" <c:if
                                                test="${profile.basicInformation.gender == Gender.MALE.name()}"> selected </c:if>>
                                            Male
                                        </option>
                                        <option name="${Gender.FEMALE}" value="${Gender.FEMALE}"
                                                <c:if
                                                        test="${profile.basicInformation.gender == Gender.FEMALE.name()}"> selected </c:if>>
                                            Female
                                        </option>
                                    </form:select>
                                </div>
                                <div class="col-sm-6">
                                    <label for="birthday" class="text-secondary"><spring:message
                                            code="basicInformationEditPage.birthday"/></label>
                                    <form:input path="birthday" type="date" class="form-control p-1"
                                                id="birthday"
                                                value="${profile.basicInformation.birthday}"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto">
                                <div class="col-sm-6">
                                    <label for="relationship"
                                           class="text-secondary"><spring:message
                                            code="basicInformationEditPage.relationship"/></label>
                                    <form:select path="relationshipId"
                                                 class="form-select form-select"
                                                 id="relationship">
                                        <c:forEach items="${relationships}" var="relationship">
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
