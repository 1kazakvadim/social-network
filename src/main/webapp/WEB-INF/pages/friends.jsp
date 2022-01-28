<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="style" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

            <div class="col-sm-8 mb-4">
                <div class="col-sm-12">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-12">
                                    <h4><span><spring:message code="friendPage.title"/></span></h4>
                                </div>
                            </div>
                            <hr>
                            <ul class="nav nav-tabs mb-3" id="myTab" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active" id="friends-tab"
                                            data-bs-toggle="tab"
                                            data-bs-target="#friends" type="button" role="tab"
                                            aria-controls="friends"
                                            aria-selected="true">
                                        <spring:message code="friendPage.tab.friends"/>
                                    </button>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link" id="friend-requests-tab"
                                            data-bs-toggle="tab"
                                            data-bs-target="#friend-requests" type="button"
                                            role="tab"
                                            aria-controls="friend-requests" aria-selected="false">
                                        <spring:message code="friendPage.tab.friendRequests"/>
                                    </button>
                                </li>
                            </ul>
                            <div class="tab-content" id="myTabContent">
                                <div class="container tab-pane fade show active" id="friends"
                                     role="tabpanel"
                                     aria-labelledby="friends-tab">
                                    <div class="row">
                                        <c:if test="${noFriendMessage != null}">
                                            <div class="col-12 d-flex justify-content-center align-content-center">
                                                <p class="text-secondary m-0 p-5">${noFriendMessage}</p>
                                            </div>
                                        </c:if>
                                        <c:forEach items="${friends}" var="friend">
                                            <div class="col-11 mb-2">
                                                <div class="d-flex">
                                                    <div class="friend-img">
                                                        <a href="<c:url value="/id${friend.user.id}"/>">
                                                            <img class="img-fluid rounded-circle"
                                                                 src="https://social-network-sam.s3.eu-north-1.amazonaws.com/${friend.profilePhotoName}"
                                                                 alt="">
                                                        </a>
                                                    </div>
                                                    <div class="friend-details">
                                                        <h5 class="mb-1"><a
                                                                href="<c:url value="/id${friend.user.id}"/>"
                                                                class="user-title text-black">${friend.basicInformation.firstname} ${friend.basicInformation.lastname}</a>
                                                        </h5>
                                                        <a href=""
                                                           class="text-secondary"><spring:message
                                                                code="friendPage.writeMessage"/></a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-1">
                                                <a class="nav-link nav-profile" href="#"
                                                   id="unfriendDropdown"
                                                   role="button"
                                                   data-bs-toggle="dropdown" data-display="static"
                                                   aria-expanded="false">
                                                    <svg xmlns="http://www.w3.org/2000/svg"
                                                         width="24"
                                                         height="24"
                                                         viewBox="0 0 24 24" fill="none"
                                                         stroke="currentColor"
                                                         stroke-width="2"
                                                         stroke-linecap="round"
                                                         stroke-linejoin="round"
                                                         class="feather feather-more-horizontal align-middle">
                                                        <circle cx="12" cy="12" r="1"></circle>
                                                        <circle cx="19" cy="12" r="1"></circle>
                                                        <circle cx="5" cy="12" r="1"></circle>
                                                    </svg>
                                                </a>
                                                <ul class="dropdown-menu dropdown-menu-end"
                                                    aria-labelledby="unfriendDropdown">
                                                    <li>
                                                        <a href="<c:url value="/id${friend.user.id}/unfriend"/>"
                                                           class="dropdown-item"><spring:message
                                                                code="friendPage.unfriend"/></a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <hr>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="container tab-pane fade" id="friend-requests"
                                     role="tabpanel"
                                     aria-labelledby="friend-requests-tab">
                                    <div class="row">
                                        <c:if test="${noFriendRequestMessage != null}">
                                            <div class="col-12 d-flex justify-content-center align-content-center">
                                                <p class="text-secondary m-0 p-5">${noFriendRequestMessage}</p>
                                            </div>
                                        </c:if>
                                        <c:forEach items="${friendRequests}" var="friendRequest">
                                            <div class="col-11 mb-2">
                                                <div class="d-flex">
                                                    <div class="friend-img">
                                                        <a href="<c:url value="/id${friendRequest.user.id}"/>">
                                                            <img class="img-fluid rounded-circle"
                                                                 src="https://social-network-sam.s3.eu-north-1.amazonaws.com/${friendRequest.profilePhotoName}"
                                                                 alt="">
                                                        </a>
                                                    </div>
                                                    <div class="friend-details">
                                                        <h5 class="mb-1"><a
                                                                href="<c:url value="/id${friendRequest.user.id}"/>"
                                                                class="user-title text-black">${friendRequest.basicInformation.firstname} ${friendRequest.basicInformation.lastname}</a>
                                                        </h5>
                                                        <a href=""
                                                           class="text-secondary"><spring:message
                                                                code="friendPage.writeMessage"/></a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-1">
                                                <a class="nav-link nav-profile" href="#"
                                                   id="requestDropdown"
                                                   role="button"
                                                   data-bs-toggle="dropdown" data-display="static"
                                                   aria-expanded="false">
                                                    <svg xmlns="http://www.w3.org/2000/svg"
                                                         width="24"
                                                         height="24"
                                                         viewBox="0 0 24 24" fill="none"
                                                         stroke="currentColor"
                                                         stroke-width="2"
                                                         stroke-linecap="round"
                                                         stroke-linejoin="round"
                                                         class="feather feather-more-horizontal align-middle">
                                                        <circle cx="12" cy="12" r="1"></circle>
                                                        <circle cx="19" cy="12" r="1"></circle>
                                                        <circle cx="5" cy="12" r="1"></circle>
                                                    </svg>
                                                </a>
                                                <ul class="dropdown-menu dropdown-menu-end"
                                                    aria-labelledby="requestDropdown">
                                                    <li>
                                                        <a href="<c:url value="/id${friendRequest.user.id}/accept-request"/>"
                                                           class="dropdown-item"><spring:message
                                                                code="friendPage.acceptRequest"/></a>
                                                    </li>
                                                    <li>
                                                        <a href="<c:url value="/id${friendRequest.user.id}/cancel-request"/>"
                                                           class="dropdown-item"><spring:message
                                                                code="friendPage.cancelRequest"/></a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <hr>
                                        </c:forEach>
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
