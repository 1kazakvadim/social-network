<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style:styles/>
    <style:error/>
    <title>Social Network</title>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="../header.jsp"/>

<section>
    <div class="container-lg">
        <div class="row d-flex justify-content-center align-items-center">
            <div class="error">
                <div class="error-code">
                    <h3>Oops! Page not found</h3>
                    <h1><span>4</span><span>0</span><span>3</span></h1>
                </div>
                <h2>You don`t have permission to access / on this server</h2>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../footer.jsp"/>

<style:scripts/>

</body>
</html>
