<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/static/js/bootstrap.bundle.min.js" var="bootstrap"/>
<spring:url value="/resources/static/js/jquery-3.3.1.min.js" var="jquery"/>
<spring:url value="/resources/static/js/alert.js" var="alert"/>
<script src="${bootstrap}"></script>
<script src="${jquery}"></script>
<script src="${alert}"></script>