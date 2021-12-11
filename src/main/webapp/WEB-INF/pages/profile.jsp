<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="style" tagdir="/WEB-INF/tags" %>
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
            <div class="col-sm-8">
                <div class="container">
                    <div class="row gutters-sm">
                        <div class="col-md-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="d-flex flex-column align-items-center text-center">
                                        <img src="<c:url value="/resources/static/images/profile-photo.png"/>"
                                             class="rounded-circle" width="150" alt="profile-photo">
                                        <div class="mt-3">
                                            <h4>John Doe</h4>
                                            <p class="text-secondary mb-1">Full Stack Developer</p>
                                            <p class="text-muted font-size-sm">Brest, Belarus</p>
                                            <button class="btn btn-primary"><spring:message code="profilePage.add"/></button>
                                            <button class="btn btn-outline-primary"><spring:message code="profilePage.message"/></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card mt-3">
                                <div class="hidden-xs hidden-sm">
                                    <ul class="profile-info-list">
                                        <li class="friend-title"><a href="#"><spring:message code="profilePage.friends"/> (9)</a></li>
                                        <li class="friend-img-list">
                                            <div class="friend-row">
                                                <a href="#"><img src="<c:url value="/resources/static/images/friend1.png"/>" alt=""/></a>
                                                <a href="#"><img src="<c:url value="/resources/static/images/friend2.png"/>" alt=""/></a>
                                                <a href="#"><img src="<c:url value="/resources/static/images/friend3.png"/>" alt=""/></a>
                                                <a href="#"><img src="<c:url value="/resources/static/images/friend4.png"/>" alt=""/></a>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="card mt-3">
                                <ul class="list-group list-group-flush social-contact">
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <h6 class="mb-0">
                                            <i class="icon-github icon-large"></i>
                                            <span>Github</span>
                                        </h6>
                                        <a class="text-secondary" href="#">Github</a>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <h6 class="mb-0">
                                            <i class="icon-twitter icon-large"></i>
                                            <span>Twitter</span>
                                        </h6>
                                        <a class="text-secondary" href="#">Twitter</a>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <h6 class="mb-0">
                                            <i class="icon-instagram icon-large"></i>
                                            <span>Instagram</span>
                                        </h6>
                                        <a class="text-secondary" href="#">Instagram</a>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <h6 class="mb-0">
                                            <i class="icon-facebook icon-large"></i>
                                            <span>Facebook</span>
                                        </h6>
                                        <a class="text-secondary" href="#">Facebook</a>
                                    </li>
                                    <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                                        <h6 class="mb-0">
                                            <i class="icon-skype icon-large"></i>
                                            <span>Skype</span>
                                        </h6>
                                        <a class="text-secondary" href="#">Skype</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <div class="card mb-3">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-sm-10">
                                            <h4 class="mb-0"><span>John Doe</span></h4>
                                        </div>
                                        <div class="col-sm-2 profile-edit">
                                            <a class="" href="#"><spring:message code="profilePage.edit"/></a>
                                        </div>
                                    </div>
                                    <hr>
                                    <div class="row mb-2">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0"><span><spring:message code="profilePage.email"/></span></h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <span>email@example.com</span>
                                        </div>
                                    </div>
                                    <div class="row mb-2">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">
                                                <span><spring:message code="profilePage.phone"/></span>
                                            </h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <span>(29) 111-11-11</span>
                                        </div>
                                    </div>
                                    <div class="row mb-2">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">
                                                <span><spring:message code="profilePage.mobile"/></span></h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <span>(162) 11-11-11</span>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-sm-3">
                                            <h6 class="mb-0">
                                                <span><spring:message code="profilePage.address"/></span>
                                            </h6>
                                        </div>
                                        <div class="col-sm-9 text-secondary">
                                            <span>Brest, Belarus</span>
                                        </div>
                                    </div>
                                    <hr>
                                </div>
                            </div>
                            <div class="card mb-3">
                                <div class="card-body pt-0">
                                    <div class="row">
                                        <div>
                                            <div class="row">
                                                <div class="photo-title"><a href="#"><spring:message code="profilePage.photos"/> (159)</a></div>
                                                <div class="col-lg-4 col-md-12 mb-4 mb-lg-0">
                                                    <div class="rounded">
                                                        <a href="#"><img src="<c:url value="/resources/static/images/photo1.jpg"/>" class="w-100"/></a>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 mb-4 mb-lg-0">
                                                    <div class="rounded">
                                                        <a href="#"><img src="<c:url value="/resources/static/images/photo2.jpg"/>" class="w-100"/></a>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4 mb-4 mb-lg-0">
                                                    <div class="rounded">
                                                        <a href="#"><img src="<c:url value="/resources/static/images/photo3.png"/>" class="w-100"/></a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>

<style:scripts/>

</body>
</html>
