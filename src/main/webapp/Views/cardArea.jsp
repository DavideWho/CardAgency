<%@ page import="com.example.cardagency.Util.Operazione" %>

<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: david
  Date: 12/08/2023
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Card Area</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/Styles/divBase.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/Styles/navbar.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/Styles/cardStyle.css">

    <script src="<%= request.getContextPath()%>/Scripts/cardAreaScripts.js.js"></script>
    <script src="<%= request.getContextPath()%>/Scripts/tablesMenagementScripts.js"></script>

</head>
<body <% if (request.getAttribute("operazioni") != null) { %> onload="toggleTable()" <% } %> style="background-color: navy; color: white;">
    <jsp:include page="../Components/loggedNavbar.jsp" />
  <h1> Dettagli Carta </h1>


    <%
        // Recupera dati dalla sessione
        String name = (String)session.getAttribute("name");
        String surname = (String)session.getAttribute("surname");
        String status = (String)session.getAttribute("status");
        String nCard = (String)session.getAttribute("nCard");
        String lastFourDigits = nCard.substring(nCard.length() - 4);
        String saldo = session.getAttribute("saldo").toString();

        // Verifica se i valori sono presenti nella sessione
        if (name != null && surname != null && status != null){
            if(status.equals("0")){
    %>
                <div class="container my-3">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="d-flex justify-content-start">
                                <div class="d-flex justify-content-center container text-white mt-5">
                                    <div class="card p-2 px-3 py-3" style="background-color: #D1B000;">
                                        <div class="d-flex justify-content-between align-items-center"><img src="https://i.imgur.com/8ANWXql.png" width="20" height="20"><img src="https://i.imgur.com/SsTSozr.png" width="40"></div>
                                        <div class="mt-3"><span class="mr-3">****</span><span class="mr-3">****</span><span class="mr-3">****</span><span class="mr-2"><%=lastFourDigits%></span></div>
                                        <div class="d-flex justify-content-between card-details mt-3 mb-3">
                                            <div class="d-flex flex-column"><span class="light">Titolare</span><span><%=name%> <%=surname%></span></div>
                                            <div class="d-flex flex-row">
                                                <div class="d-flex flex-column mr-3"><span class="light">Saldo</span><span><%=saldo%>€</span></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
      <%
              }else{
      %>
                  <script>
                      showAlertIfAllarme();
                  </script>
      <%
              }
          }
      %>

  </div>
  <br><br>
    <div class="container my-5">
      <form id="myForm" action="${pageContext.request.contextPath}/history-servlet" method="post">
          <label>Genera report operazioni:</label>
          <input type="hidden" name="nCard" value=${nCard}>
          <button id="showHideButton" type="submit" class="btn btn-primary" onclick="toggleTable()">Storico Carta</button>
      </form>
      <script>
          toggleTable();
      </script>

      <!-- Tendina che mostra la tabella (inizialmente nascosta) -->
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
          </style>
          <table>
              <tr>
                  <th>Movimenti</th>
              </tr>
              <tr>
                  <th>Codice Operazione</th>
                  <th>Operazione</th>
                  <th>Importo</th>
                  <th>Codice Operatore</th>
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
                            if(x.getTipologia().equals("accredito")){
              %>
                                <tr style="background-color: lightgreen; color: black;">
                  <%
                            }else if(x.getTipologia().equals("addebito")){
                  %>
                                <tr style="background-color: lightcoral; color: black;">
                  <%
                            }else{
                  %>
                                <tr style="background-color: lightskyblue; color: black;">
                  <%
                            }
                  %>
                                    <td> <%= x.getIdOperazione() %></td>
                                    <td> <%= x.getTipologia() %></td>
                            <% if(x.getImporto() != null) { %>
                                    <td> <%= x.getImporto() + " €" %> </td>
                            <%} else{ %>
                                    <td><td><%= "0" %></td>
                            <%} %>
                                    <td> <%= x.getOperatore() %> </td>
                                    <td> <%= x.getDataOra() %> </td>
                                </tr>
            <%
                    }
             %>
          </table>
          <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
      </div>
  </div>
    <jsp:include page="../Components/footer2.jsp" />
</body>
</html>
