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

