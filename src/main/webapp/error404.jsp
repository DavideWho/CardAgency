<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Errore 404 - Pagina non trovata</title>
    <link rel="stylesheet" type="text/css" href="Styles/navbar.css">
    <link rel="stylesheet" type="text/css" href="Styles/indexStyle.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<jsp:include page="Components/unloggedNavbar.jsp" />

<div class="content">
    <h1>Errore 404 - Pagina non trovata</h1>
    <p style="color: white;">La pagina richiesta non è disponibile.</p>
    <!-- Aggiungi qui eventuali informazioni o istruzioni aggiuntive per l'utente -->
</div>

<jsp:include page="Components/footer.jsp" />
</body>
</html>
