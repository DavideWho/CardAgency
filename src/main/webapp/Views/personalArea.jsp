<%--
  Created by IntelliJ IDEA.
  User: david
  Date: 12/08/2023
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Personal Area</title>
    <!-- Add Bootstrap CSS Link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom CSS links here -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Styles/divBase.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Styles/navbar.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/Styles/general.css">
    <script src="<%= request.getContextPath()%>/Scripts/cardAreaScripts.js"></script>
</head>
<body style="background-color: navy; color: white;">
    <jsp:include page="../Components/loggedNavbar.jsp" />
    <div class="container my-5">
        <h1>Bentornato nella tua area personale!</h1>
    </div>
    <div class="container my-3">
        <div class="row">
            <div class="col-md-6">
                <div class="d-flex justify-content-start">
                    <%
                        // Recupera name e surname dalla sessione
                        String name = (String) session.getAttribute("name");
                        String surname = (String) session.getAttribute("surname");

                        // Verifica se i valori sono presenti nella sessione
                        if (name != null && surname != null) {
                    %>
                    <label><h4>Bentornato </h4></label>
                    <h4 class="mr-2">  <%= name %></h4>
                    <h4><%= surname %>!</h4>
                    <%
                    } else {
                    %>
                    <h3>No user information available</h3>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
    <br><br>
    <div class="contair mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <form action="${pageContext.request.contextPath}/cardDetails-servlet" method="post">
                    <div class="form-group">
                        <label>Inserire numero carta:</label>
                        <input type="text" class="form-control" pattern="[0-9]{16}" maxlength="16" placeholder="1111222233334444" name="nCard">
                    </div>
                    <button type="submit" class="btn btn-primary" id="submitCard" name="enter">ENTER</button>
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="../Components/footer2.jsp" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

