<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
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
                <form action="${pageContext.request.contextPath}/login" method="POST">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <div class="form-outline mb-4">
                        <input type="text" id="form3Example3" class="form-control form-control-lg"
                               placeholder="<spring:message code="loginPage.placeholder.email"/>"
                               name="username"/>
                    </div>
                    <div class="form-outline mb-3">
                        <input type="password" id="form3Example4"
                               class="form-control form-control-lg"
                               placeholder="<spring:message code="loginPage.placeholder.password"/>"
                               name="password"/>
                    </div>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="form-check mb-0">
                            <input class="form-check-input me-2" type="checkbox" value=""
                                   id="form2Example3"/>
                            <label class="form-check-label" for="form2Example3">
                                <spring:message code="loginPage.rememberMe"/>
                            </label>
                        </div>
                        <a href="#!" class="text-body"><spring:message
                                code="loginPage.forgotPassword"/>?</a>
                    </div>
                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="btn btn-primary btn-lg"
                                style="padding-left: 2.5rem; padding-right: 2.5rem;">
                            <spring:message code="loginPage.signIn"/>
                        </button>
                        <p class="small fw-bold mt-2 pt-1 mb-0"><spring:message
                                code="loginPage.noAccount"/>? <a href="#!"
                                                                 class="link-danger"><spring:message
                                code="loginPage.register"/></a>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>
