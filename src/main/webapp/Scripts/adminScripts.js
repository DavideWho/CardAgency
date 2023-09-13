// Mostra un alert se l'esito è presente e non vuoto
function showAlertIfEsito2(esito) {
    if (esito && esito !== "") {
        if (window.confirm(esito)) {
            window.history.back(); // Torna alla pagina precedente se confermato
        }
    }
}

// Mostra un alert se la comunicazione è presente
function showAlertIfComunicazione(comunicazione) {
    if (comunicazione) {
        if (window.confirm(comunicazione)) {
            window.history.back(); // Torna alla pagina precedente se confermato
        }
    }
}

// Mostra un alert se ci sono nuove notizie
function showAlertIfNews(news) {
    if (news) {
        if (window.confirm(news)) {
            window.history.back(); // Torna alla pagina precedente se confermato
        }
    }
}

// Conferma l'invio del modulo con un alert di conferma
function confirmSubmit() {
    // Mostra un alert di conferma all'utente
    var result = confirm("Sei sicuro di voler procedere?");
    // Ricarica la pagina se l'utente conferma
    location.reload(true);
}

// Esegue l'hashing della password e imposta il valore nell'input nascosto
function hashAndSubmit() {
    // Recupera la password in chiaro
    var passwordInput = document.getElementById("psw");
    var password = passwordInput.value;

    // Esegue l'hashing della password utilizzando js-sha256 (presumendo che js-sha256 sia stato importato)
    var hashedPassword = sha256(password);

    // Imposta il valore dell'input nascosto per la password hashata
    var hashedPasswordInput = document.getElementById("hashed-psw");
    hashedPasswordInput.value = hashedPassword;

    // Puoi eseguire ulteriori convalide qui, se necessario
    // Ad esempio, verifica che gli altri campi siano validi

    // Restituisci true per consentire l'invio del modulo o false per impedirlo
    return true;
}

// Mostra un alert se c'è una nuova registrazione utente
function showAlertIfNewUser(newUser) {
    if (newUser && newUser !== "") {
        var esito = newUser;
        if (window.confirm(esito)) {
            window.history.back(); // Torna alla pagina precedente se confermato
        }
    }
}
