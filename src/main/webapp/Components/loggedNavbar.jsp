<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Navbar Example</title>
  <link rel="stylesheet" type="text/css" href="src/main/webapp/Styles/navbar.css">
</head>
<body>
<!-- Recupero sessione -->
<%
  session = request.getSession();
  String target = "";
  if(session.getAttribute("tipo")!=null) {
    if (session.getAttribute("tipo").equals("cliente"))
      target = "/Views/personalArea.jsp";
    else if (session.getAttribute("tipo").equals("dealer"))
      target = "/Views/dealerArea.jsp";
    else
      target = "/Views/adminArea.jsp";
  }
%>

<nav class="navbar">
  <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">Home</a>

  <a class="navbar-brand" href="<%=request.getContextPath() + target%>">
    <img src="${pageContext.request.contextPath}/Components/Imgs/user-icon.png" height="35" width="45">
  </a>
  <div class="navbar-right">
    <form action="${pageContext.request.contextPath}/logout-servlet" method="post">
      <button class="login-button" type="submit">Logout</button>
    </form>
  </div>
</nav>
</body>
</html>
