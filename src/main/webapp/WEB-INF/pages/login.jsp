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
                <c:if test="${param.error!=null}">
                    <div class="alert alert-danger" role="alert">
                        <spring:message code="loginPage.invalidEmailPassword"/>
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/login" method="POST">
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <div class="mb-4">
                        <div class="form-floating">
                            <input type="text" id="email" class="form-control form-control-lg"
                                   name="username" placeholder="E-mail"/>
                            <label class="text-secondary" for="email"><spring:message
                                    code="loginPage.email"/></label>
                        </div>
                    </div>
                    <div class="mb-3">
                        <div class="form-floating">
                            <input type="password" id="password"
                                   class="form-control form-control-lg" name="password"
                                   placeholder="Password"/>
                            <label class="text-secondary" for="password"><spring:message
                                    code="loginPage.password"/></label>
                        </div>
                    </div>
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="form-check mb-0">
                            <input class="form-check-input me-2" type="checkbox" name="remember-me"
                                   id="remember-me"/>
                            <label class="form-check-label" for="remember-me">
                                <spring:message code="loginPage.rememberMe"/>
                            </label>
                        </div>
                        <a href="#" class="text-body"><spring:message
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
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>
