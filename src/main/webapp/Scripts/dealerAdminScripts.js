// Mostra un alert se è stata creata una nuova carta
function showAlertIfNewCard(newNcard) {
    var userFlag = "Conto aperto. N. Carta: " + newNcard;

    if (window.confirm(userFlag)) {
        // L'utente ha confermato l'alert, quindi reindirizziamo
        window.history.back();
    }
}

// Mostra un alert in base a una determinata flag
function showAlertIfFlag(flag) {
    var userFlag = "";

    if (flag == 1)
        userFlag = "Account eliminato.";
    else if (flag == 2)
        userFlag = "Impossibile eliminare l'account. È associato a carte non vuote.";
    else if (flag == 3)
        userFlag = "Nessun account corrispondente ai dati inseriti.";

    if (window.confirm(userFlag)) {
        // L'utente ha confermato l'alert, quindi reindirizziamo
        window.history.back();
    }
}

// Mostra un alert se è stato creato un nuovo utente
function showAlertIfNewUser(newUser) {
    if (window.confirm(newUser)) {
        // L'utente ha confermato l'alert, quindi reindirizziamo
        window.history.back();
    }
}

// Mostra un alert in base allo stato
function showAlertIfStato(stato) {
    var esito = "";

    if (stato == "0")
        esito = "Impossibile effettuare l'operazione, carta bloccata. Contattare un admin.";
    else if (stato == "1")
        esito = "Saldo aggiornato con successo.";
    else if (stato == "2")
        esito = "Credito insufficiente per l'addebito.";

    if (window.confirm(esito)) {
        // L'utente ha confermato l'alert, quindi reindirizziamo
        window.history.back();
    }
}

// Esegui l'hashing della password e invia il modulo
function hashAndSubmit() {
    // Recupera la password in chiaro
    var passwordInput = document.getElementById("password");
    var password = passwordInput.value;

    // Esegui l'hashing della password utilizzando js-sha256
    var hashedPassword = sha256(password);

    // Imposta il valore dell'input nascosto per la password hashata
    var hashedPasswordInput = document.getElementById("hashed-password");
    hashedPasswordInput.value = hashedPassword;

    // Invia il modulo
    var form = document.getElementById("registration-form");
    form.submit();
}

// Esegui l'hashing della password e invia il modulo per la cancellazione
function hashAndSubmit2() {
    // Recupera la password in chiaro
    var passwordInput = document.getElementById("psw2");
    var password = passwordInput.value;

    // Esegui l'hashing della password utilizzando js-sha256
    var hashedPassword = sha256(password);

    // Imposta il valore dell'input nascosto per la password hashata
    var hashedPasswordInput = document.getElementById("hashed-psw");
    hashedPasswordInput.value = hashedPassword;

    // Invia il modulo
    var form = document.getElementById("delete-form");
    form.submit();
}
