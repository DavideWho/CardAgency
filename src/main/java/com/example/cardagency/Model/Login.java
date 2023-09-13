package com.example.cardagency.Model;

import com.example.cardagency.Util.Autenticazione;
import com.example.cardagency.Util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    /**
     * Autentica un utente cercando nelle credenziali nel database.
     *
     * @param email     L'indirizzo email dell'utente da autenticare.
     * @param hashedPsw La password dell'utente criptata da confrontare con quella memorizzata nel database.
     * @return Un oggetto Autenticazione contenente le informazioni dell'utente autenticato o null se l'autenticazione fallisce.
     */
    public static Autenticazione authenticateUser(String email, String hashedPsw) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            try {
                String query = "SELECT idUser, name, surname, tipo FROM user WHERE email = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                try {
                    stmt.setString(1, email);
                    stmt.setString(2, hashedPsw);
                    ResultSet rs = stmt.executeQuery();
                    try {
                        if (rs.next()) {
                            int idUser = rs.getInt("idUser");
                            String name = rs.getString("name");
                            String surname = rs.getString("surname");
                            String type = rs.getString("tipo");
                            // Crea un oggetto Autenticazione con le informazioni dell'utente autenticato
                            Autenticazione autenticazione = new Autenticazione(idUser, name, surname, type);
                            return autenticazione;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
