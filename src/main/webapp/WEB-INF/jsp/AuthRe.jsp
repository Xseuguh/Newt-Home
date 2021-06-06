<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <%@page import="java.io.File"%>
    <title>Bienvenue chez Newt'Home</title>

    <link
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstr
ap.min.css"
      rel="stylesheet"
    />
    <link
      type="text/css"
      href="<%=request.getContextPath()%>/css/generalCSS.css"
      rel="stylesheet"
    />
    <link
      type="text/css"
      href="<%=request.getContextPath()%>/css/AuthRe.css"
      rel="stylesheet"
    />
    <script src="https://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
      integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
      crossorigin="anonymous"
    ></script>
    <script
      id="scriptBTS"
      src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"
    ></script>

    <script src="<%=request.getContextPath()%>/js/AuthRe.js"></script>
  </head>

  <body>
    <jsp:include page="Header.jsp" />

    <div class="forms">
      <ul class="tab-group">
        <li class="tab active"><a href="#login">Log In</a></li>
        <li class="tab"><a href="#signup">Sign Up</a></li>
      </ul>
      <form action="login" id="login" method="post">
        <h1>Login</h1>
        <div class="input-field">
          <label for="mail">Email</label>
          <input type="email" name="mail" required />
          <label for="password">Password</label>
          <input type="password" name="password" required />
          <input type="submit" value="Login" class="button" />
        </div>
      </form>

      <form
        action="saveSignUpForm"
        id="signup"
        method="post"
        modelAttribute="signUpForm"
      >
        <h1>Sign Up</h1>
        <div class="input-field">
          <label>Email</label>
          <input type="email" path="email" name="email" required />
          <label>Nom</label>
          <input type="text" path="nom" name="nom" required />
          <label>Prenom</label>
          <input type="text" path="prenom" name="prenom" required />
          <label>Password</label>
          <input
            type="password"
            path="password"
            name="password"
            minlength="8"
            required
          />
          <label>Date de naissance(vous devez être majeur !)</label>
          <input type="Date" path="birthday" name="birthday" required />

          <input type="submit" value="Sign up" class="button" />
        </div>
      </form>
    </div>

    <jsp:include page="Footer.jsp" />
  </body>
</html>
