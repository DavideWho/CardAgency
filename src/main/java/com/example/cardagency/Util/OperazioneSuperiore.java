package com.example.cardagency.Util;

public class OperazioneSuperiore extends OperazioneGenerica {
    // Beneficiario dell'operazione
    private String beneficiario;

    /**
     * Costruttore della classe OperazioneSuperiore.
     *
     * @param idOperazione Identificatore dell'operazione.
     * @param operatore    Nome dell'operatore che ha effettuato l'operazione.
     * @param tipologia    Tipologia dell'operazione.
     * @param dataOra      Data e ora in cui è stata effettuata l'operazione.
     * @param beneficiario Beneficiario dell'operazione.
     */
    public OperazioneSuperiore(String idOperazione, String operatore, String tipologia, String dataOra, String beneficiario) {
        super(idOperazione, operatore, tipologia, dataOra);
        this.beneficiario = beneficiario;
    }

    /**
     * Restituisce il beneficiario dell'operazione superiore.
     *
     * @return Beneficiario dell'operazione.
     */
    public String getBeneficiario() {
        return this.beneficiario;
    }

    /**
     * Imposta il beneficiario dell'operazione superiore.
     *
     * @param beneficiario Nuovo beneficiario dell'operazione.
     */
    public void setBeneficiario(String beneficiario) {
        this.beneficiario = beneficiario;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto OperazioneSuperiore.
     *
     * @return Stringa con i valori dei campi dell'operazione superiore.
     */
    public String toString() {
        String var10000 = super.getIdOperazione();
        return "OperazioneSuperiore{idOperazioneSuperiore='" + var10000 + "', operatore='" + super.getOperatore() + "', tipologia='" + super.getTipologia() + "'beneficiario='" + this.beneficiario + "'}";
    }
}
