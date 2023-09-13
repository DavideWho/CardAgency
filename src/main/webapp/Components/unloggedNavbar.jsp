<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar Example</title>
    <!-- <link rel="stylesheet" href="src/main/webapp/Styles/navbar.css"> Assicurati di includere il tuo foglio di stile CSS -->
</head>
<body>
<nav class="navbar">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">Home</a>
    <div class="navbar-right">
        <a class="login-button" href="${pageContext.request.contextPath}/Views/loginArea.jsp">Login</a>
    </div>
</nav>
</body>
</html>
