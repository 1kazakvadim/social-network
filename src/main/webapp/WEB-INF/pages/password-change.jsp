<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
                                        code="passwordChangePage.title"/></span></h4>
                            </div>
                        </div>
                        <hr>
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
                        <form:form action="password-change" method="POST"
                                   id="password-change">
                            <div class="row mb-3 mx-auto justify-content-center text-center">
                                <p class="text-secondary"><spring:message
                                        code="passwordChangePage.currentPassword"/></p>
                                <div class="col-8">
                                    <input type="password" id="password" maxlength="20"
                                           aria-label="password"
                                           class="form-control" name="password"
                                           placeholder="<spring:message code="passwordChangePage.placeholder.password"/>"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto justify-content-center text-center">
                                <p class="text-secondary"><spring:message
                                        code="passwordChangePage.createPassword"/></p>
                                <div class="col-8">
                                    <input type="password" id="new-password" maxlength="20"
                                           aria-label="new-password" class="form-control"
                                           name="newPassword"
                                           placeholder="<spring:message code="passwordChangePage.placeholder.newPassword"/>"/>
                                </div>
                            </div>
                            <div class="row mb-3 mx-auto justify-content-center text-center">
                                <div class="col-8">
                                    <input type="password" id="re-password" maxlength="20"
                                           aria-label="re-password" class="form-control"
                                           name="confirmPassword"
                                           placeholder="<spring:message code="passwordChangePage.placeholder.confirmPassword"/>"/>
                                </div>
                            </div>
                            <hr>
                            <button type="submit" class="btn btn-primary btn-sm d-block mx-auto">
                                <span class="p-4"><spring:message code="button.save"/></span>
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
