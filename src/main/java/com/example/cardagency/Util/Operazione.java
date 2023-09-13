package com.example.cardagency.Util;

public class Operazione extends OperazioneGenerica {
    // Importo dell'operazione
    private String importo;
    // Numero di carta associato all'operazione
    private String nCarta;

    /**
     * Costruttore della classe Operazione.
     *
     * @param idOperazione Identificatore dell'operazione.
     * @param operatore    Nome dell'operatore che ha effettuato l'operazione.
     * @param tipologia    Tipologia dell'operazione.
     * @param dataOra      Data e ora in cui è stata effettuata l'operazione.
     * @param importo      Importo dell'operazione.
     * @param nCarta       Numero di carta associato all'operazione.
     */
    public Operazione(String idOperazione, String operatore, String tipologia, String dataOra, String importo, String nCarta) {
        super(idOperazione, operatore, tipologia, dataOra);
        this.importo = importo;
        this.nCarta = nCarta;
    }

    /**
     * Restituisce l'importo dell'operazione.
     *
     * @return Importo dell'operazione.
     */
    public String getImporto() {
        return this.importo;
    }

    /**
     * Imposta l'importo dell'operazione.
     *
     * @param importo Nuovo importo dell'operazione.
     */
    public void setImporto(String importo) {
        this.importo = importo;
    }

    /**
     * Restituisce il numero di carta associato all'operazione.
     *
     * @return Numero di carta associato all'operazione.
     */
    public String getnCarta() {
        return this.nCarta;
    }

    /**
     * Imposta il numero di carta associato all'operazione.
     *
     * @param nCarta Nuovo numero di carta associato all'operazione.
     */
    public void setnCarta(String nCarta) {
        this.nCarta = nCarta;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Operazione.
     *
     * @return Stringa con i valori dei campi dell'operazione.
     */
    public String toString() {
        String var10000 = super.getIdOperazione();
        return "Operazione{idOperazione='" + var10000 + "', tipologia='" + super.getIdOperazione() + "'importo='" + this.importo + "', nCarta='" + this.nCarta + "', dataOra='" + super.getDataOra() + "'}";
    }
}
