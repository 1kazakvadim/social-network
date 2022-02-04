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
                <form:form action="recover-password" method="POST">
                    <div class="mb-4">
                        <p><spring:message
                                code="recoverPasswordPage.title"/></p>
                        <div class="form-floating">
                            <input type="email" id="email" maxlength="255" aria-label="email"
                                   class="form-control" name="email"/>
                            <label class="text-secondary" for="email"><spring:message
                                    code="loginPage.email"/></label>
                        </div>
                    </div>
                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="btn btn-primary btn-lg">
                            <span class="p-4"><spring:message
                                    code="button.send"/></span>
                        </button>
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
