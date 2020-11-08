<%--
  Created by IntelliJ IDEA.
  User: koala
  Date: 07.11.2020
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>My posts</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css"
          integrity="sha384-DhY6onE6f3zzKbjUPRc2hOzGAdEf4/Dz+WJwBvEYL/lkkIsI3ihufq9hk9K4lVoK" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
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
                                   required
                                   pattern="^(?=.*[A-Z].*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{6,}$">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputPasswordRepeatSignUp" class="col-sm-4 col-form-label">Repeat password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPasswordRepeatSignUp" required
                                   pattern="^(?=.*[A-Z].*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{6,}$">
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
<main>
    <div class="d-flex justify-content-center" id="allPosts">

    </div>
</main>
<footer class="page-footer font-small blue fixed-bottom ml-3" style="position: absolute; background-color: cornflowerblue">

    <!-- Copyright -->
    <div class="footer-copyright text-center py-3">© 2020 Copyright:
        <a href="https://vk.com/koala1101"> vk.com/koala1101</a>
    </div>
    <!-- Copyright -->

</footer>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/js/bootstrap.bundle.min.js" integrity="sha384-BOsAfwzjNJHrJ8cZidOg56tcQWfp6y72vEJ8xQ9w6Quywb24iOsW913URv1IS4GD" crossorigin="anonymous"></script>
<script src="/js/myPosts.js" type="text/javascript"></script>
</body>
</html>
