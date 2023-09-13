<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 12/08/2023
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Area</title>
    <!-- Add Bootstrap CSS Link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom CSS links here -->
    <link rel="stylesheet" type="text/css" href="../Styles/divBase.css">
    <link rel="stylesheet" type="text/css" href="../Styles/navbar.css">
    <link rel="stylesheet" type="text/css" href="../Styles/indexStyle.css">
    <link rel="stylesheet" type="text/css" href="../Styles/general.css">
    <script src="../Scripts/loginScripts.js"></script>
</head>
<body>
    <jsp:include page="../Components/unloggedNavbar.jsp" />

    <div class="container mt-5">
        <h2 style="color: white;"><%= "Prego, autenticarsi per favore." %></h2>
        <br>
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form action="${pageContext.request.contextPath}/login-servlet" id="registration-form" method="post">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                    </div>
                    <input type="hidden" id="hashed-password" name="hashedPsw">
                    <button type="submit" class="btn btn-primary" onclick="hashAndSubmit()">Login</button>
                </form>
                <script src="https://cdn.jsdelivr.net/npm/js-sha256@0.9.0"></script>
            </div>
        </div>
    </div>
    <br><br>
    <jsp:include page="../Components/footer.jsp" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

