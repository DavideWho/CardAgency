package com.example.cardagency.Model;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import com.example.cardagency.Util.DatabaseUtil;
import com.example.cardagency.Util.Carta;

public class CardDetails {

    /**
     * Trova i dettagli di una carta dato il suo numero e l'id del proprietario.
     *
     * @param nCard          Il numero della carta da cercare.
     * @param idProprietario L'ID del proprietario della carta.
     * @return Un oggetto Carta contenente i dettagli della carta trovata, o null se non trovata.
     */
    public static Carta findCardDetails(String nCard, String idProprietario) {
        try {
            // Ottieni una connessione al database
            Connection conn = DatabaseUtil.getConnection();

            try {
                // Query SQL per selezionare i dettagli della carta
                String query = "SELECT idCard, saldo, status FROM card WHERE nCard = ? AND idProprietario = ?";
                PreparedStatement stmt = conn.prepareStatement(query);

                try {
                    // Imposta i parametri nella query
                    stmt.setString(1, nCard);
                    stmt.setString(2, idProprietario);
                    ResultSet rs = stmt.executeQuery();

                    try {
                        if (rs.next()) {
                            // Estrai i dati dal risultato della query
                            String idCard = rs.getString("idCard");
                            Double saldo = Double.valueOf(rs.getString("saldo"));
                            String status = rs.getString("status");

                            // Crea un oggetto Carta con i dettagli trovati
                            Carta x = new Carta(nCard, saldo, status, idCard);
                            return x;
                        }
                        System.out.println("<h1>Cards not found or invalid credentials</h1>");
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("<h1>Error accessing the database</h1>");
        }
        return null;
    }
}
