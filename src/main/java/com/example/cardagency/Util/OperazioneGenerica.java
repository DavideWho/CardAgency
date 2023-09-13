package com.example.cardagency.Util;

public class OperazioneGenerica {
    // Identificatore dell'operazione
    private String idOperazione;
    // Nome dell'operatore che ha effettuato l'operazione
    private String operatore;
    // Tipologia dell'operazione
    private String tipologia;
    // Data e ora in cui è stata effettuata l'operazione
    private String dataOra;

    /**
     * Costruttore della classe OperazioneGenerica.
     *
     * @param idOperazione Identificatore dell'operazione.
     * @param operatore    Nome dell'operatore che ha effettuato l'operazione.
     * @param tipologia    Tipologia dell'operazione.
     * @param dataOra      Data e ora in cui è stata effettuata l'operazione.
     */
    public OperazioneGenerica(String idOperazione, String operatore, String tipologia, String dataOra) {
        this.idOperazione = idOperazione;
        this.operatore = operatore;
        this.tipologia = tipologia;
        this.dataOra = dataOra;
    }

    /**
     * Restituisce l'identificatore dell'operazione.
     *
     * @return Identificatore dell'operazione.
     */
    public String getIdOperazione() {
        return this.idOperazione;
    }

    /**
     * Imposta l'identificatore dell'operazione.
     *
     * @param idOperazione Nuovo identificatore dell'operazione.
     */
    public void setIdOperazione(String idOperazione) {
        this.idOperazione = idOperazione;
    }

    /**
     * Restituisce il nome dell'operatore che ha effettuato l'operazione.
     *
     * @return Nome dell'operatore.
     */
    public String getOperatore() {
        return this.operatore;
    }

    /**
     * Imposta il nome dell'operatore che ha effettuato l'operazione.
     *
     * @param operatore Nuovo nome dell'operatore.
     */
    public void setOperatore(String operatore) {
        this.operatore = operatore;
    }

    /**
     * Restituisce la tipologia dell'operazione.
     *
     * @return Tipologia dell'operazione.
     */
    public String getTipologia() {
        return this.tipologia;
    }

    /**
     * Imposta la tipologia dell'operazione.
     *
     * @param tipologia Nuova tipologia dell'operazione.
     */
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    /**
     * Restituisce la data e l'ora in cui è stata effettuata l'operazione.
     *
     * @return Data e ora dell'operazione.
     */
    public String getDataOra() {
        return this.dataOra;
    }

    /**
     * Imposta la data e l'ora dell'operazione.
     *
     * @param dataOra Nuova data e ora dell'operazione.
     */
    public void setDataOra(String dataOra) {
        this.dataOra = dataOra;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto OperazioneGenerica.
     *
     * @return Stringa con i valori dei campi dell'operazione.
     */
    public String toString() {
        return "OperazioneGenerica{idOperazione='" + this.idOperazione + "', operatore='" + this.operatore + "', tipologia='" + this.tipologia + "', dataOra='" + this.dataOra + "'}";
    }
}
