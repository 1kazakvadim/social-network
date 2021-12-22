<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style:styles/>
    <title>Social Network</title>
</head>

<body class="d-flex flex-column min-vh-100">

<jsp:include page="header.jsp"/>

<section>
    <div class="container-lg">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col-md-8 col-lg-6 col-xl-4">
                <div>
                    <c:if test="${messageError != null}">
                        <div class="alert alert-danger" role="alert">
                                ${messageError}
                        </div>
                    </c:if>
                    <c:if test="${messageSuccess != null}">
                        <div class="alert alert-success" role="alert">
                                ${messageSuccess}
                        </div>
                    </c:if>
                    <form:form action="register" method="POST" modelAttribute="basicInformation">
                        <div class="row mb-3">
                            <div class="col">
                                <div class="form-floating">
                                    <form:input path="firstname" type="text" id="firstname"
                                                class="form-control"/>
                                    <label class="text-secondary" for="firstname"><spring:message
                                            code="registerPage.firstname"/></label>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-floating">
                                    <form:input path="lastname" type="text" id="lastname"
                                                class="form-control"/>
                                    <label class="text-secondary" for="lastname"><spring:message
                                            code="registerPage.lastname"/></label>
                                </div>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label class="text-secondary" for="birthday"><spring:message
                                        code="registerPage.birthday"/></label>
                                <form:input path="birthday" type="date"
                                            class="form-control text-secondary" id="birthday"/>
                            </div>
                            <div class="col">
                                <p class="text-secondary mb-0"><spring:message
                                        code="registerPage.gender"/></p>
                                <div class="btn-group">
                                    <form:radiobutton path="gender" checked="true" class="btn-check"
                                                      name="gender" id="male"
                                                      autocomplete="off" value="MALE"/>
                                    <label class="btn btn-outline-primary"
                                           for="male"><spring:message
                                            code="registerPage.gender.male"/></label>
                                    <form:radiobutton path="gender" class="btn-check"
                                                      name="gender" id="female"
                                                      autocomplete="off" value="FEMALE"/>
                                    <label class=" btn btn-outline-primary"
                                           for="female"><spring:message
                                            code="registerPage.gender.female"/></label>
                                </div>
                            </div>
                        </div>
                        <div class="mb-3">
                            <div class="form-floating">
                                <input type="email" id="email" class="form-control" name="email"/>
                                <label class="text-secondary" for="email"><spring:message
                                        code="registerPage.email"/></label>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <div class="form-floating">
                                    <input type="password" id="password" class="form-control"
                                           name="password"/>
                                    <label class="text-secondary" for="password"><spring:message
                                            code="registerPage.password"/></label>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-floating">
                                    <input type="password" id="confirm-password"
                                           class="form-control" name="confirmPassword">
                                    <label class="text-secondary"
                                           for="confirm-password"><spring:message
                                            code="registerPage.confirmPassword"/></label>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="form-check mb-0">
                                <input class="form-check-input me-2" type="checkbox"
                                       value="true"
                                       id="termsAndConditions" name="termsAndConditions"/>
                                <label class="form-check-label" for="termsAndConditions">
                                    <spring:message
                                            code="registerPage.termsAndConditions"/>
                                </label>
                            </div>
                        </div>
                        <div class="text-center text-lg-start mt-4 pt-2">
                            <button type="submit" class="btn btn-primary btn-lg">
                                <span class="p-4"><spring:message
                                        code="registerPage.signUp"/></span>
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>