package com.example.cardagency.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    // URL di connessione al database MariaDB
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/cardagency";
    // Nome utente del database
    private static final String JDBC_USER = "root";
    // Password per l'autenticazione al database
    private static final String JDBC_PASSWORD = "root";

    /**
     * Costruttore vuoto per la classe DatabaseUtil.
     */
    public DatabaseUtil() {
    }

    /**
     * Ottiene una connessione al database MariaDB.
     *
     * @return Oggetto Connection che rappresenta la connessione al database.
     * @throws SQLException Eccezione lanciata in caso di errore nella connessione al database.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Carica il driver JDBC per MariaDB
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver JDBC per MariaDB caricato con successo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ATTENZIONE: Driver JDBC per MariaDB non caricato");
        }
        // Restituisce una connessione al database utilizzando le credenziali specificate
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}
