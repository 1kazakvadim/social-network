<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style:styles/>
    <title>Social Network</title>
</head>

<body class="d-flex flex-column min-vh-100">

<jsp:include page="header.jsp"/>

<section>
    <div class="container">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="col-md-8 col-lg-6 col-xl-4">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger" role="alert">
                        <spring:message code="loginPage.invalidEmailPassword"/>
                    </div>
                </c:if>
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
                <form:form action="login" method="POST" id="signin-form">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <div class="mb-4">
                        <div class="form-floating">
                            <input type="email" id="email" maxlength="255"
                                   class="form-control form-control-lg" aria-label="email"
                                   name="username"/>
                            <label class="text-secondary" for="email"><spring:message
                                    code="loginPage.email"/></label>
                        </div>
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <input type="password" id="password" maxlength="20"
                                   aria-label="password"
                                   class="form-control form-control-lg" name="password"/>
                            <label class="text-secondary" for="password"><spring:message
                                    code="loginPage.password"/></label>
                        </div>
                    </div>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="form-check mb-0">
                            <input class="form-check-input me-2" type="checkbox" name="remember-me"
                                   aria-label="remember-me"
                                   id="remember-me"/>
                            <label class="form-check-label" for="remember-me">
                                <spring:message code="loginPage.rememberMe"/>
                            </label>
                        </div>
                        <a href="<c:url value='/recover-password'/>"
                           class="text-body"><spring:message
                                code="loginPage.forgotPassword"/>?</a>
                    </div>
                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="btn btn-primary btn-lg">
                            <span class="p-4"><spring:message code="loginPage.signIn"/></span>
                        </button>
                        <p class="small fw-bold mt-2 pt-1 mb-0"><spring:message
                                code="loginPage.noAccount"/>? <a href="<c:url value='/register'/>"
                                                                 class="link-danger"><spring:message
                                code="loginPage.register"/></a>
                        </p>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>
