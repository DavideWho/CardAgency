// Mostra un alert se si verifica un allarme specifico (es. "CARTA BLOCCATA")
function showAlertIfAllarme() {
    var allarme = "CARTA BLOCCATA";

    // Mostra un alert con il testo dell'allarme e attende la conferma dell'utente
    if (window.confirm(allarme)) {
        // L'utente ha confermato l'alert, quindi reindirizziamo alla pagina precedente
        window.history.back();
    }
}
