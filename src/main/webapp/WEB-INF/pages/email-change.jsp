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
                                        code="emailChangePage.title"/></span></h4>
                            </div>
                        </div>
                        <hr>
                        <form:form action="email-change" method="POST" id="email-change">
                            <div class="row mb-3 mx-auto justify-content-center text-center">
                                <p class="text-secondary"><spring:message
                                        code="emailChangePage.currentEmail"/>: ${email}</p>
                                <div class="col-6">
                                    <input type="text" id="email" class="form-control" name="email"
                                           maxlength="255" aria-label="email"
                                           placeholder="<spring:message
                                        code="emailChangePage.placeholder.newEmail"/>"/>
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
            <c:if test="${messageError != null}">
                <div class="alert text-white alert-notification-error align-middle w-auto"
                     role="alert">
                        ${messageError}
                </div>
            </c:if>
            <c:if test="${messageSuccess != null}">
                <div class="alert text-white alert-notification-success align-middle w-auto"
                     role="alert">
                        ${messageSuccess}
                </div>
            </c:if>
            <jsp:include page="edit-nav.jsp"/>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>
