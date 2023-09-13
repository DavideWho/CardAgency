package com.example.cardagency.Util;

public class Carta {
    private String nCard;
    private Double saldo;
    private String status;
    private String idCard;

    /**
     * Costruttore della classe Carta.
     *
     * @param nCard  Numero della carta.
     * @param saldo  Saldo associato alla carta.
     * @param status Stato della carta (bloccata o sbloccata).
     * @param idCard Identificatore della carta.
     */
    public Carta(String nCard, Double saldo, String status, String idCard) {
        this.nCard = nCard;
        this.saldo = saldo;
        this.status = status;
        this.idCard = idCard;
    }

    /**
     * Restituisce il numero della carta.
     *
     * @return Il numero della carta.
     */
    public String getnCard() {
        return this.nCard;
    }

    /**
     * Imposta il numero della carta.
     *
     * @param nCard Il numero della carta da impostare.
     */
    public void setnCard(String nCard) {
        this.nCard = nCard;
    }

    /**
     * Restituisce il saldo associato alla carta.
     *
     * @return Il saldo associato alla carta.
     */
    public Double getSaldo() {
        return this.saldo;
    }

    /**
     * Imposta il saldo associato alla carta.
     *
     * @param saldo Il saldo da impostare.
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    /**
     * Restituisce lo stato della carta (bloccata o sbloccata).
     *
     * @return Lo stato della carta.
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Imposta lo stato della carta (bloccata o sbloccata).
     *
     * @param status Lo stato da impostare.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Restituisce l'identificatore della carta.
     *
     * @return L'identificatore della carta.
     */
    public String getIdCard() {
        return this.idCard;
    }

    /**
     * Imposta l'identificatore della carta.
     *
     * @param idCard L'identificatore della carta da impostare.
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
