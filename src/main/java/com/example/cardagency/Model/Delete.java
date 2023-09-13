package com.example.cardagency.Model;

import com.example.cardagency.Util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete {

    /**
     * Cancella un account utente e verifica le condizioni per farlo.
     *
     * @param name    Il nome dell'utente da cancellare.
     * @param surname Il cognome dell'utente da cancellare.
     * @param email   L'email dell'utente da cancellare.
     * @param psw     La password dell'utente da cancellare.
     * @return Una stringa che rappresenta il risultato dell'operazione:
     *         - "0" se si è verificato un errore nell'accesso al database.
     *         - "1" se l'account è stato cancellato con successo.
     *         - "2" se ci sono ancora carte associate all'account.
     *         - "3" se non esiste un account corrispondente ai dati forniti.
     */
    public static String deleteAccount(String name, String surname, String email, String psw) {
        Integer cards = -1;
        try {
            // Ottieni una connessione al database
            Connection conn = DatabaseUtil.getConnection();

            try {
                // Query SQL per selezionare l'ID dell'utente in base ai dati forniti
                String query = "SELECT idUser FROM user WHERE name = ? AND surname = ? AND email = ? and password = ?";
                PreparedStatement stmt = conn.prepareStatement(query);

                try {
                    // Imposta i parametri nella query
                    stmt.setString(1, name);
                    stmt.setString(2, surname);
                    stmt.setString(3, email);
                    stmt.setString(4, psw);
                    ResultSet rs = stmt.executeQuery();

                    try {
                        if (!rs.next()) {
                            String s = "3"; // Nessun account trovato
                            return s;
                        }
                        String idUser = rs.getString("idUser");

                        // Verifica quante carte sono associate all'account con saldo positivo
                        query = "SELECT COUNT(*) AS count_cards FROM card WHERE idProprietario = ? AND saldo > 0";
                        PreparedStatement stmt2 = conn.prepareStatement(query);

                        try {
                            stmt2.setString(1, idUser);
                            ResultSet rs2 = stmt2.executeQuery();

                            try {
                                if (rs2.next()) {
                                    cards = Integer.valueOf(rs2.getString("count_cards"));
                                }
                            }
                            catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }

                        // Se ci sono carte con saldo positivo, non è possibile cancellare l'account
                        if (cards != 0) {
                            String s2 = "2"; // Ci sono carte associate con saldo positivo
                            return s2;
                        }

                        // Cancellazione dell'account utente
                        query = "DELETE FROM user WHERE idUser = ?";
                        PreparedStatement stmt3 = conn.prepareStatement(query);

                        try {
                            stmt3.setString(1, idUser);
                            int rs3 = stmt3.executeUpdate();
                            if (rs3 > 0) {
                                String s3 = "1"; // Account cancellato con successo
                                return s3;
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
        return "0"; // Errore nell'accesso al database
    }
}
