package com.example.cardagency.Util;

public class Autenticazione {
    private int idUser;
    private String name;
    private String surname;
    private String type;

    /**
     * Costruttore della classe Autenticazione.
     *
     * @param idUser   Identificatore dell'utente.
     * @param name     Nome dell'utente.
     * @param surname  Cognome dell'utente.
     * @param type     Tipo di utente.
     */
    public Autenticazione(int idUser, String name, String surname, String type) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.type = type;
    }

    /**
     * Restituisce l'identificatore dell'utente.
     *
     * @return L'identificatore dell'utente.
     */
    public int getIdUser() {
        return this.idUser;
    }

    /**
     * Imposta l'identificatore dell'utente.
     *
     * @param idUser L'identificatore dell'utente da impostare.
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Restituisce il nome dell'utente.
     *
     * @return Il nome dell'utente.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param name Il nome dell'utente da impostare.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce il cognome dell'utente.
     *
     * @return Il cognome dell'utente.
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Imposta il cognome dell'utente.
     *
     * @param surname Il cognome dell'utente da impostare.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Restituisce il tipo di utente.
     *
     * @return Il tipo di utente.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Imposta il tipo di utente.
     *
     * @param type Il tipo di utente da impostare.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Restituisce una rappresentazione in formato stringa dell'oggetto Autenticazione.
     *
     * @return Una stringa che rappresenta l'oggetto Autenticazione.
     */
    @Override
    public String toString() {
        return "Autenticazione{" +
                "idUser=" + idUser +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
