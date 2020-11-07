<%--
  Created by IntelliJ IDEA.
  User: koala
  Date: 24.10.2020
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Forum - home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css"
          integrity="sha384-DhY6onE6f3zzKbjUPRc2hOzGAdEf4/Dz+WJwBvEYL/lkkIsI3ihufq9hk9K4lVoK" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<!--navigation-->
<nav class="navbar sticky-top navbar-expand-lg navbar-light">
    <div class="container-fluid" style="background-color: aliceblue">
        <a href="${pageContext.request.contextPath}/" class="navbar-brand ml-3" style="color: blue">Forum for KFU
            students</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent"
                aria-controls="navbarContent" aria-expanded="false">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="btn btn-link ml-auto" href="" role="button">Home</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-link ml-auto" href="${pageContext.request.contextPath}/forum"
                       role="button">Forum</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-link ml-auto" href="${pageContext.request.contextPath}/about" role="button">About
                        us</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-link ml-auto" href="${pageContext.request.contextPath}/members" role="button">Members</a>
                </li>
            </ul>
            <c:if test="${pageContext.session.getAttribute('id') == null}">
                <div class="d-flex justify-content-start">
                    <button class="btn btn-outline-dark my-3" data-toggle="modal" data-target="#signInModal">Sign in
                    </button>
                    <button class="btn btn-outline-dark my-3 ml-3" data-toggle="modal" data-target="#signUpModal">Sign
                        up
                    </button>
                </div>
            </c:if>
            <c:if test="${pageContext.session.getAttribute('id')!=null}">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/profile">
                    <img src="/img/" alt="" class="mr-3"> Profile
                </a>
            </c:if>
        </div>
    </div>
</nav>

<!--Modals-->
<div class="modal fade" id="signInModal" role="dialog" tabindex="-1" aria-labelledby="signInModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="signInModalLabel">Sign in:</h5>
                <button type="button" class="btn-close" data-dismiss="modal" aria-label="close">
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/signIn" method="post">
                <div class="modal-body">
                    <div class="row mb-3">
                        <label for="inputEmailSignIn" class="col-sm-4 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmailSignIn" name="email" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputPasswordSignIn" class="col-sm-4 col-form-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPasswordSignIn" name="password"
                                   required>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-outline-primary my-3">Authorization</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="signUpModal" role="dialog" tabindex="-1" aria-labelledby="signUpModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="signUpModalLabel">Sign up:</h5>
                <button class="btn-close" data-dismiss="modal" aria-label="close">
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/signUp" method="post">
                <div class="modal-body">
                    <div class="row mb-3">
                        <label for="inputFirstNameSignUp" class="col-sm-4 col-form-label">First name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputFirstNameSignUp" name="firstName" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputLastNameSignUp" class="col-sm-4 col-form-label">Last name</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputLastNameSignUp" name="lastName" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputEmailSignUp" class="col-sm-4 col-form-label">Email</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="inputEmailSignUp" name="email" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputPasswordSignUp" class="col-sm-4 col-form-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPasswordSignUp" name="password"
                                   required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputPasswordRepeatSignUp" class="col-sm-4 col-form-label">Repeat password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPasswordRepeatSignUp" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputAgeSignUp" class="col-sm-4 col-form-label">Date of birth</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control" id="inputAgeSignUp" name="dateOfBirth" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <legend class="col-form-label col-sm-10">I agree with <a href="">Rules</a>
                        </legend>
                        <div class="col-sm-2">
                            <div class="form-check">
                                <input type="checkbox" id="termAcceptedSignUp" class="form-check-input mt-2" required>
                            </div>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-outline-primary my-3">Sign up</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="createPost" role="dialog" tabindex="-1" aria-labelledby="createPostLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createPostLabel">Create new post:</h5>
                <button class="btn-close" data-dismiss="modal" aria-label="close">
                </button>
            </div>
            <form action="${pageContext.request.contextPath}/createPost" method="post">
                <div class="modal-body">
                    <div class="row mb-3">
                        <label for="inputNameCreatePost" class="col-sm-4 col-form-label">Name of Post</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputNameCreatePost" name="name" required>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputTextCreatePost" class="col-sm-4 col-form-label">text of post</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputTextCreatePost" name="text" required>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-outline-primary my-3">Create</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!--Main-->
<main class="vh-100 bg-gradient-primary" style="background-color: darkblue">
    <div class="d-flex  align-items-center justify-content-center ml-3 mr-3">
        <div class="vh-100 d-flex flex-column align-items-center justify-content-center h4"
             style="background-color: aliceblue; width: 20%; ">
            <img src="/img/profile-icon.webp" style="position: relative; height: 100px; width: 100px">
            <p class="ml-3 mr-3">${pageContext.request.session.getAttribute('firstName')} ${pageContext.request.session.getAttribute('lastName')}</p>
            <a class="btn btn-link btn-outline-primary btn-lg ml-auto mr-auto"
               href="${pageContext.request.contextPath}/profileEdit" role="button">Edit profile</a>
            <br> <br>
            <a class="btn btn-link btn-lg ml-auto mr-auto" href="${pageContext.request.contextPath}/myPosts"
               role="button">My posts</a>
            <button class="btn btn-link btn-lg ml-auto mr-auto" role="button" data-toggle="modal"
                    data-target="#createPost">Create new post
            </button>
            <a class="btn btn-link btn-lg ml-auto mr-auto" href="${pageContext.request.contextPath}/signOut"
               role="button">Sign out</a>

        </div>

        <div class="d-flex flex-column ml-3 mr-3" id="" style="background-color: aliceblue">
            <h3>Your last post</h3>
            <p class="font-weight-light ml-3 mr-3">0 (yours likes) 0 (commentaries)</p>
            <hr>
            <div id="lastPost">
                <h4 class="ml-3 mr-3">Name of post</h4>
                <p class="ml-3 mr-3">Here u can see your post</p>
            </div>

        </div>
    </div>
</main>

<c:if test="${pageContext.request.session.getAttribute('error') != null}">

    <c:choose>
        <c:when test="${pageContext.request.session.getAttribute('error') =='-1'}">
            <script>
                console.log("Error = -1");
                window.onload = function () {
                    alert("User with this email doesn't exist");
                }
            </script>
        </c:when>
        <c:when test="${pageContext.request.session.getAttribute('error') == '-2'}">
            <script>
                console.log("Error = -2");
                window.onload = function () {
                    alert("Wrong login or password");
                }
            </script>
        </c:when>
    </c:choose>
</c:if>

<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js"
        integrity="sha384-BOsAfwzjNJHrJ8cZidOg56tcQWfp6y72vEJ8xQ9w6Quywb24iOsW913URv1IS4GD"
        crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/post.js"></script>
</body>
</html>
