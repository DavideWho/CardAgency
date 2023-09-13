<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.cardagency.Util.OperazioneSuperiore" %>
<%@ page import="com.example.cardagency.Util.OperazioneGenerica" %>
<%@ page import="com.example.cardagency.Util.Operazione" %>
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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Area</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/Styles/divBase.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/Styles/navbar.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/Styles/general.css">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
    <script src="<%= request.getContextPath()%>/Scripts/adminScripts.js"></script>
    <script src="<%= request.getContextPath()%>/Scripts/tablesMenagementScripts.js"></script>
</head>
<body <% if (request.getAttribute("operazioni") != null) { %> onload="toggleTable()" <% } %>>
    <jsp:include page="../Components/loggedNavbar.jsp" />
    <div class="container mt-5">
        <h1>Admin Area!</h1>
    </div>
    <div class="container my-5">
        <% if(request.getAttribute("newUser") != null && !((String)request.getAttribute("newUser")).isEmpty()){ %>
            <script>
                showAlertIfNewUser("<%= (String)request.getAttribute("newUser") %>");
            </script>
        <%
            }
        %>
        <% if(request.getAttribute("esito2") != null && !((String)request.getAttribute("esito2")).isEmpty()){
            String esito2 = (String)request.getAttribute("esito2");
            System.out.println("Esito2: " + esito2);
        %>
            <script>
                showAlertIfEsito2("<%= esito2 %>")
            </script>
        <%
            }
        if(request.getAttribute("comunicazione") != null){ %>
            <script>
                showAlertIfComunicazione("<%= (String)request.getAttribute("comunicazione") %>");
            </script>
        <%
            }
        %>
        <% if (request.getAttribute("news") != null) { %>
            <script>
                showAlertIfNews("Nuovo negoziante: <%= request.getAttribute("news")%>");
            </script>
        <% }
            // Recupera name e surname dalla sessione
            String name = (String) session.getAttribute("name");
            String surname = (String) session.getAttribute("surname");
            String idAdmin = session.getAttribute("idUser").toString();

            // Verifica se i valori sono presenti nella sessione
            if (name != null && surname != null) {
        %>
                <div class="container my-3">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="d-flex justify-content-start">
                                <label><h4>Bentornato </h4></label>
                                <h4 class="mr-2"> <%= name %></h4>
                                <h4><%= surname %>!</h4>
                            </div>
                        </div>
                    </div>
                </div>
        <%
            }else{
        %>
                <div class="container mt-3">
                    <div class="row">
                        <div class="col-md-6">
                            <h4>No user information available</h4>
                        </div>
                    </div>
                </div>
        <%
            }
        %>
    </div>
    <br><br>
    <div class="container my-5">
        <form action="${pageContext.request.contextPath}/lockUnlock-servlet" method="post" onsubmit="return confirmSubmit();">
            <div class="form-group">
                <label for="nCard">Inserire numero carta:</label>
                <input type="text" class="form-control" id="nCard" name="nCard">
            </div>
            <div class="form-group">
                <label for="operation">Scegliere operazione:</label>
                <select class="form-control" id="operation" name="operation">
                    <option value="lock">Blocca</option>
                    <option value="unlock">Sblocca</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary" name="apply">APPLY</button>
        </form>
    </div>

    <br><br>
    <div class="container my-5">
        <form id="reportForm" action="${pageContext.request.contextPath}/reportAdmin-servlet" method="post">
            <input type="hidden" name="idAdmin" value="<%=idAdmin%>">
            <% if(request.getAttribute("operazioni")!=null){ %>
                <div class="my-div">
                    <p>Filtra le operazioni per Tipologia:</p>
                    <select class="form-control" name="filter1">
                        <option value="sbloccoCarta">Sblocco</option>
                        <option value="bloccoCarta">Blocco</option>
                        <option value="">Rimuovi filtro</option>
                    </select>
                </div>
                <div class="my-div">
                    <p>Filtra le operazioni superiori per Tipologia:</p>
                    <select class="form-control" name="filter2">
                        <option value="Abilita Negoziante">Abilita</option>
                        <option value="Disabilita Negoziante">Disabilita</option>
                        <option value="">Rimuovi filtro</option>
                    </select>
                </div>
                <div class="my-div">
                    <p>Filtra tutte le operazioni per Data:</p>
                    <input type="date" class="form-control" name="filter3">
                </div>
                <button id="showHideButton" type="submit" class="btn btn-primary" onclick="toggleTable()">Filtra</button>
            <%
                }else{
            %>
                    <label for="reportForm">Genera report operazioni:</label>
                    <button id="showHideButton" type="submit" class="btn btn-primary" onclick="toggleTable()">REPORT</button>
            <%
                }
            %>
        </form>
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
                    <th>Operazioni Carte</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th>Codice Operatore</th>
                    <th>Codice Operazione</th>
                    <th>Operazione</th>
                    <th>N.Carta</th>
                    <th>Data/Ora</th>
                    <th>
                        <button class="btn btn-success" onclick="exportTable('pdf')">Esporta in PDF</button>
                        <button class="btn btn-success" onclick="exportTable('json')">Esporta in JSON</button>
                        <button class="btn btn-success" onclick="exportTable('xml')">Esporta in XML</button>
                    </th>
                </tr>

                <%
                    int flag = 0;
                    ArrayList<OperazioneGenerica> operazioni = (ArrayList<OperazioneGenerica>) request.getAttribute("operazioni");

                    if(operazioni != null)
                        for(OperazioneGenerica x:operazioni){
                            if (!(x instanceof OperazioneSuperiore)) {
                                Operazione operazione = (Operazione) x;
                %>
                                <tr>
                                    <td> <%= operazione.getOperatore()%></td>
                                    <td> <%= operazione.getIdOperazione() %></td>
                                    <td> <%= operazione.getTipologia() %></td>
                                    <td> <%= operazione.getnCarta() %> </td>
                                    <td> <%= operazione.getDataOra() %> </td>
                                </tr>
                <%
                            }else{
                                OperazioneSuperiore operazione = (OperazioneSuperiore) x;
                                if(flag == 0){
                %>
                                    <tr>
                                        <th>Operazioni Negozi</th>
                                    </tr>
                                    <tr>
                                        <th>Codice Operatore</th>
                                        <th>Codice Operazione</th>
                                        <th>Operazione</th>
                                        <th>Codice beneficiario</th>
                                        <th>Data/Ora</th>
                                    </tr>
                <%
                                    flag = 1;
                                }
                %>
                                <tr>
                                    <td> <%= operazione.getOperatore()%></td>
                                    <td> <%= operazione.getIdOperazione() %></td>
                                    <td> <%= operazione.getTipologia() %></td>
                                    <td> <%= operazione.getBeneficiario()%></td>
                                    <td> <%= operazione.getDataOra() %></td>
                                </tr>
                <%
                            }
                        }
                %>
                </tbody>
            </table>
        </div>
    </div>
    <br><br>
    <div class="container my-5">
        <h4>Abilita negoziante</h4>
        <form action="${pageContext.request.contextPath}/enable-servlet" id="enable-form" method="post" onsubmit="return hashAndSubmit()">
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
                <input type="password" class="form-control" id="psw" name="psw" placeholder="Password" required>
            </div>
            <input type="hidden" id="hashed-psw" name="hashedPsw">
            <input type="hidden" name="type" value="dealer">
            <button type="submit" class="btn btn-primary">Abilita</button>
        </form>
        <script src="https://cdn.jsdelivr.net/npm/js-sha256@0.9.0"></script>
    </div>
    <br><br>
    <div class="container my-5">
        <h4>Disabilita Negoziante</h4>
        <form action="${pageContext.request.contextPath}/disableDealer-servlet" method="post">
            <div class="form-group">
                <input type="text" class="form-control" name="idDealer" placeholder="Codice Negoziante" required>
            </div>
            <button type="submit" class="btn btn-danger" name="disable">Abilita-Disabilita</button>
        </form>
    </div>
    <jsp:include page="../Components/footer2.jsp" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
