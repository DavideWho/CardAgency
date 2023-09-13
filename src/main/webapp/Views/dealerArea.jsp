<%@ page import="com.example.cardagency.Util.Operazione" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: david
  Date: 12/08/2023
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dealer Area</title>
    <!-- Add Bootstrap CSS Link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Add your custom CSS links here -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Styles/divBase.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Styles/navbar.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/Styles/general.css">
    <!-- Add your custom JavaScript links here -->
    <script src="<%=request.getContextPath()%>/Scripts/dealerAdminScripts.js"></script>
    <script src="<%=request.getContextPath()%>/Scripts/tablesMenagementScripts.js"></script>
</head>
<body <% if (request.getAttribute("operazioni") != null) { %> onload="toggleTable()" <% } %>>
    <jsp:include page="../Components/loggedNavbar.jsp" />
    <div class="container mt-5">
        <h1>Dealer Area!</h1>
    </div>
    <div class="container my-5">
        <div class="row">
            <div class="col-md-6">
                <%
                    if(request.getAttribute("newNcard") != null){
                        if(!request.getAttribute("newNcard").equals("-1")){
                %>
                        <script>
                            showAlertIfNewCard("<%= request.getAttribute("newNcard")%>");
                        </script>
                <%
                        }
                    }
                    if(request.getAttribute("flag") != null){
                %>
                        <script>
                            showAlertIfFlag("<%= request.getAttribute("flag")%>");
                        </script>
                <%
                    }
                    if(request.getAttribute("newUser") != null){
                        String newUser = request.getAttribute("newUser").toString();
                %>
                        <script>
                            showAlertIfNewUser("<%= request.getAttribute("newUser")%>");
                        </script>
                <%
                    }
                    if(request.getAttribute("stato") != null){ %>
                        <script>
                            showAlertIfStato("<%= (String)request.getAttribute("stato")%>");
                        </script>
                <%
                    }
                %>
            </div>
        </div>
    </div>
    <div class="container my-3">
        <div class="row">
            <div class="col-md-6">
                <div class="d-flex justify-content-start">
                    <%
                        // Recupera name e surname dalla sessione
                        String name = (String) session.getAttribute("name");
                        String surname = (String) session.getAttribute("surname");
                        String idDealer = session.getAttribute("idUser").toString();
                        // Verifica se i valori sono presenti nella sessione
                        if (name != null && surname != null) {
                    %>
                    <label><h4>Bentornato </h4></label>
                    <h4 class="mr-2"> <%= name %></h4>
                    <h4><%= surname %>!</h4>
                    <%
                    } else {
                    %>
                    <h4>No user information available</h4>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
    <div class="container my-5">
        <form action="${pageContext.request.contextPath}/cardOperations-servlet" method="post">
            <div class="form-group">
                <label for="nCard">Inserire numero carta:</label>
                <input type="text" class="form-control" id="nCard" name="nCard" required>
            </div>
            <div class="form-group">
                <label for="amount">Inserire importo:</label>
                <input type="text" class="form-control" id="amount" name="amount" pattern="[0-9]*\.?[0-9]{0,2}" title="Inserisci solo numeri o numeri decimali con massimo 2 cifre dopo il punto" required>
            </div>
            <div class="form-group">
                <label for="operation">Scegliere operazione:</label>
                <select class="form-control" id="operation" name="operation">
                    <option value="addebito">Addebito</option>
                    <option value="accredito">Accredito</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary" name="apply">APPLY</button>
        </form>
    </div>
    <div class="container my-5">
        <form id="reportForm" action="${pageContext.request.contextPath}/reportDealer-servlet" method="post">
            <label for="reportForm">Genera report operazioni:</label>
            <input type="hidden" name="idDealer" value="<%=idDealer%>">
            <button id="showHideButton" type="submit" class="btn btn-primary" onclick="toggleTable()">REPORT</button>
        </form>
        <script>
            toggleTable();
        </script>
        <div id="tableDiv" style="display: none;">
            <style>
                /* Stile per la tabella */
                table {
                    border-collapse: collapse;
                    width: 100%;
                }

                /* Stile per le celle della tabella */
                table, th, td {
                    border: 1px solid black;
                    padding: 8px;
                    text-align: center;
                }
                th, td, tr{
                    color: white !important;
                    background-color: #003366 !important;
                }
            </style>
            <table class="table">
                <thead>
                <tr>
                    <th>Operazioni</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>Codice Operazione</th>
                    <th>Operazione</th>
                    <th>Importo</th>
                    <th>N.Carta</th>
                    <th>Data/Ora</th>
                    <th>
                        <button class="btn btn-success" onclick="exportTable('pdf')">Esporta in PDF</button>
                        <button class="btn btn-success" onclick="exportTable('json')">Esporta in JSON</button>
                        <button class="btn btn-success" onclick="exportTable('xml')">Esporta in XML</button>
                    </th>
                </tr>

                <%
                    ArrayList<Operazione> operazioni = (ArrayList<Operazione>) request.getAttribute("operazioni");
                    if(operazioni != null)
                        for(Operazione x:operazioni){
                %>
                <tr>
                    <td> <%= x.getIdOperazione() %></td>
                    <td> <%= x.getTipologia() %></td>
                    <td> <%= x.getImporto() + " €" %> </td>
                    <td> <%= x.getnCarta() %> </td>
                    <td> <%= x.getDataOra() %> </td>
                </tr>
                <%
                        }
                %>
                </tbody>
            </table>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
        </div>
    </div>
    <div class="container my-5">
        <h4>Registra nuovo utente</h4>
        <form action="${pageContext.request.contextPath}/register-servlet" id="registration-form" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="name" placeholder="Nome" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="surname" placeholder="Cognome" required>
            </div>
            <div class="form-group">
                <input type="email" class="form-control" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="psw" placeholder="Password" required>
            </div>
            <input type="hidden" id="hashed-password" name="hashedPsw">
            <input type="hidden" name="type" value="cliente">
            <button type="submit" class="btn btn-primary" onclick="hashAndSubmit()">Registra</button>
        </form>
    </div>
    <div class="container my-5">
        <h4>Apri nuovo conto</h4>
        <form action="${pageContext.request.contextPath}/newCard-servlet" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="idUser" placeholder="42042" required>
            </div>
            <button type="submit" class="btn btn-primary" name="register">Registra</button>
        </form>
    </div>
    <div class="container my-5">
        <h4>Elimina utente</h4>
        <form action="${pageContext.request.contextPath}/deleteAccount-servlet" id="delete-form" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="name2" placeholder="Nome" required>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="surname2" placeholder="Cognome" required>
            </div>
            <div class="form-group">
                <input type="email" class="form-control" name="email2" placeholder="Email" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="psw2" name="psw2" placeholder="Password" required>
            </div>
            <input type="hidden" id="hashed-psw" name="hashedPsw">
            <button type="submit" class="btn btn-danger" onclick="hashAndSubmit2()">Elimina</button>
        </form>
    </div>
    <jsp:include page="../Components/footer.jsp" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
