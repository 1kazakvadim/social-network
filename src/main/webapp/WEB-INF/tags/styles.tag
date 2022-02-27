<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url value="/resources/static/css/bootstrap.min.css" var="bootstrap"/>
<link href="${bootstrap}" rel="stylesheet"/>

<spring:url value="/resources/static/css/font-awesome/css/font-awesome.min.css" var="fontAwesome"/>
<link href="${fontAwesome}"
      rel="stylesheet">

<spring:url value="/resources/static/css/style.css" var="style"/>
<link href="${style}" rel="stylesheet"/>

<spring:url value="/resources/static/images/favicon.ico" var="favicon"/>
<link rel="shortcut icon" type="image/jpg" href="${favicon}"/>
