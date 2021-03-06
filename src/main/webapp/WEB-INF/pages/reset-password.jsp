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
                <div>
                    <form:form action="${token}" method="POST">
                        <div class="row mb-3">
                            <p><spring:message
                                    code="resetPasswordPage.title"/></p>
                            <div class="col">
                                <div class="form-floating">
                                    <input type="password" id="new-password" class="form-control"
                                           maxlength="20" aria-label="password"
                                           name="newPassword"/>
                                    <label class="text-secondary" for="new-password"><spring:message
                                            code="resetPasswordPage.newPassword"/></label>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-floating">
                                    <input type="password" id="confirm-password" maxlength="20"
                                           aria-label="confirm-password"
                                           class="form-control" name="confirmPassword">
                                    <label class="text-secondary"
                                           for="confirm-password"><spring:message
                                            code="resetPasswordPage.confirmPassword"/></label>
                                </div>
                            </div>
                        </div>
                        <div class="text-center text-lg-start mt-4 pt-2">
                            <button type="submit" class="btn btn-primary btn-lg">
                                <span class="p-4"><spring:message
                                        code="button.confirm"/></span>
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
