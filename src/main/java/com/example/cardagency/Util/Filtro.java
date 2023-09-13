package com.example.cardagency.Util;

public class Filtro {
    // Filtro 1
    private String filtro1;
    // Filtro 2
    private String filtro2;
    // Filtro 3
    private String filtro3;

    /**
     * Costruttore vuoto per la classe Filtro.
     * Inizializza tutti i filtri a null.
     */
    public Filtro() {
        this.filtro1 = null;
        this.filtro2 = null;
        this.filtro3 = null;
    }

    /**
     * Costruttore della classe Filtro con tre parametri.
     *
     * @param filtro1 Valore del primo filtro.
     * @param filtro2 Valore del secondo filtro.
     * @param filtro3 Valore del terzo filtro.
     */
    public Filtro(String filtro1, String filtro2, String filtro3) {
        this.filtro1 = filtro1;
        this.filtro2 = filtro2;
        this.filtro3 = filtro3;
    }

    /**
     * Restituisce il valore del filtro 1.
     *
     * @return Valore del filtro 1.
     */
    public String getFiltro1() {
        return this.filtro1;
    }

    /**
     * Imposta il valore del filtro 1.
     *
     * @param filtro1 Nuovo valore del filtro 1.
     */
    public void setFiltro1(String filtro1) {
        this.filtro1 = filtro1;
    }

    /**
     * Restituisce il valore del filtro 2.
     *
     * @return Valore del filtro 2.
     */
    public String getFiltro2() {
        return this.filtro2;
    }

    /**
     * Imposta il valore del filtro 2.
     *
     * @param filtro2 Nuovo valore del filtro 2.
     */
    public void setFiltro2(String filtro2) {
        this.filtro2 = filtro2;
    }

    /**
     * Restituisce il valore del filtro 3.
     *
     * @return Valore del filtro 3.
     */
    public String getFiltro3() {
        return this.filtro3;
    }

    /**
     * Imposta il valore del filtro 3.
     *
     * @param filtro3 Nuovo valore del filtro 3.
     */
    public void setFiltro3(String filtro3) {
        this.filtro3 = filtro3;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Filtro.
     *
     * @return Stringa con i valori dei filtri.
     */
    public String toString() {
        return "Filtro{filtro1='" + this.filtro1 + "', filtro2='" + this.filtro2 + "', filtro3='" + this.filtro3 + "'";
    }
}
