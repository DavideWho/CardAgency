<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>CardAgency</title>
    <link rel="stylesheet" type="text/css" href="Styles/navbar.css">
    <link rel="stylesheet" type="text/css" href="Styles/indexStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
    <%
        if(session.getAttribute("idUser") == null){
    %>
            <jsp:include page="Components/unloggedNavbar.jsp" />
    <%
        }
        else{
    %>
            <jsp:include page="Components/loggedNavbar.jsp" />
    <%
        }
    %>
    <h1>
        <%= "Benvenuti in CardAgency, una carta: la tua agenzia." %>
    </h1>
    <br><br>
    <jsp:include page="Components/carousel.html" />

    <jsp:include page="Components/footer.jsp" />
</body>
</html>