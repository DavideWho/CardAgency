package com.example.cardagency.Util;

public class RiscontroOperazione {
    // Stato dell'operazione (valore predefinito: "0")
    private String stato = "0";

    /**
     * Costruttore della classe RiscontroOperazione.
     */
    public RiscontroOperazione() {
    }

    /**
     * Imposta lo stato dell'operazione.
     *
     * @param stato Nuovo stato dell'operazione.
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Restituisce lo stato dell'operazione.
     *
     * @return Stato dell'operazione.
     */
    public String getStato() {
        return this.stato;
    }
}
