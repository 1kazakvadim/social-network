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

<sec:authentication var="user" property="principal"/>
<jsp:include page="header.jsp"/>

<section>
  <div class="container">
    <div class="row">

      <jsp:include page="side-nav.jsp"/>

      <div class="col-sm-8">
        <div class="row">
          <div class="col-sm-12">
            <div class="col-sm-12">
              <div class="panel-white post">
                <div class="post-heading">
                  <div class="pull-left image">
                    <img src="https://bootdey.com/img/Content/user_1.jpg"
                         class="rounded-circle avatar"
                         alt="user profile image">
                  </div>
                  <div class="pull-left meta">
                    <div class="title h5">
                      <a href="#"><b>Brian cartelly</b></a>
                    </div>
                    <h6 class="text-muted time">12:28</h6>
                  </div>
                </div>
                <hr class="me-3 ms-3">
                <!--                                -->
                <div class="post-image d-flex justify-content-center">
                  <img src="https://images.unsplash.com/photo-1622227614434-a7a26021879f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80"
                       class="image w-50" alt="image post">
                </div>
                <hr class="me-3 ms-3">
                <div class="post-description">
                  <h4>Foto title</h4>
                  <p>Put here your foto description</p>
                  <div class="stats">
                    <a href="#" class="btn btn-default stat-item">
                      <i class="icon-thumbs-up"></i> 228
                    </a>
                  </div>
                </div>
                <div class="post-footer">
                  <div class="input-group">
                    <input class="form-control"
                           placeholder="Add a comment" type="text">
                    <span class="input-group-addon">
                            <a href="#"><i class="fa fa-edit"></i></a>
                        </span>
                  </div>
                  <ul class="comments-list">
                    <li class="comment">
                      <a class="pull-left" href="#">
                        <img class="avatar"
                             src="https://bootdey.com/img/Content/user_3.jpg"
                             alt="avatar">
                      </a>
                      <div class="comment-body">
                        <div class="comment-heading">
                          <h4 class="user">Full name 1</h4>
                          <h5 class="time">7 minutes ago</h5>
                        </div>
                        <p>This is a comment bla bla bla</p>
                      </div>
                    </li>
                    <li class="comment">
                      <a class="pull-left" href="#">
                        <img class="avatar"
                             src="https://bootdey.com/img/Content/user_2.jpg"
                             alt="avatar">
                      </a>
                      <div class="comment-body">
                        <div class="comment-heading">
                          <h4 class="user">Full name 2</h4>
                          <h5 class="time">3 minutes ago</h5>
                        </div>
                        <p>This is another comment bla bla bla</p>
                      </div>
                    </li>
                    <li class="comment">
                      <a class="pull-left" href="#">
                        <img class="avatar"
                             src="https://bootdey.com/img/Content/user_3.jpg"
                             alt="avatar">
                      </a>
                      <div class="comment-body">
                        <div class="comment-heading">
                          <h4 class="user">Full name 1</h4>
                          <h5 class="time">10 seconds ago</h5>
                        </div>
                        <p>Wow! This is a comment</p>
                      </div>
                    </li>
                  </ul>
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
