package com.example.cardagency.Model;

import com.example.cardagency.Util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class EnableDisable {

    /**
     * Abilita un negoziante nel sistema.
     *
     * @param name    Il nome del negoziante da abilitare.
     * @param surname Il cognome del negoziante da abilitare.
     * @param email   L'email del negoziante da abilitare.
     * @param psw     La password del negoziante da abilitare.
     * @param idAdmin L'ID dell'amministratore che sta abilitando il negoziante.
     * @return L'ID del negoziante appena abilitato, o "Errore" in caso di errore.
     */
    public static String enableDealer(String name, String surname, String email, String psw, String idAdmin) {
        // Verifica dei parametri di input
        if (name == null || name.equals("") || surname == null || surname.equals("") || email != null || !email.equals("") || psw != null || !psw.equals("") || idAdmin != null || !idAdmin.equals("")) {
            return null;
        }

        try {
            // Ottieni una connessione al database
            Connection conn = DatabaseUtil.getConnection();

            try {
                // Query SQL per inserire un nuovo negoziante abilitato
                String query = "INSERT INTO user (name, surname, email, password, tipo, abilitato) VALUES (?,?,?,?,?,?)";
                String query2 = "SELECT idUser FROM user WHERE name = ? AND surname = ? AND email = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(query);

                try {
                    PreparedStatement stmt2 = conn.prepareStatement(query2);

                    try {
                        // Imposta i parametri nella query
                        stmt.setString(1, name);
                        stmt.setString(2, surname);
                        stmt.setString(3, email);
                        stmt.setString(4, psw);
                        stmt.setString(5, "dealer");
                        stmt.setString(6, "1");

                        int rs = stmt.executeUpdate();
                        if (rs > 0) {
                            // Ottieni l'ID del nuovo negoziante abilitato
                            stmt2.setString(1, name);
                            stmt2.setString(2, surname);
                            stmt2.setString(3, email);
                            stmt2.setString(4, psw);
                            ResultSet rs2 = stmt2.executeQuery();

                            try {
                                while (rs2.next()) {
                                    // Registra l'operazione di abilitazione effettuata dall'amministratore
                                    query = "INSERT INTO operazionesuperiore (operatore, tipologia, beneficiario, dataOra) VALUES (?,?,?,?)";
                                    PreparedStatement stmt3 = conn.prepareStatement(query);

                                    try {
                                        stmt3.setString(1, idAdmin);
                                        stmt3.setString(2, "Abilita negoziante");
                                        stmt3.setString(3, rs2.getString("idUser"));
                                        stmt3.setString(4, LocalDateTime.now().toString());
                                        int rs3 = stmt3.executeUpdate();

                                        if (rs3 > 0) {
                                            // Restituisci l'ID del negoziante appena abilitato
                                            String s = rs2.getString("idUser");
                                            return s;
                                        }
                                    }
                                    catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            catch (SQLException e) {
                                e.printStackTrace();
                            }
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
        return "Errore";
    }

    /**
     * Disabilita o abilita un negoziante nel sistema.
     *
     * @param idDealer L'ID del negoziante da disabilitare o abilitare.
     * @param idAdmin  L'ID dell'amministratore che sta eseguendo l'operazione.
     * @return Un messaggio che conferma l'operazione effettuata.
     */
    public static String disableDealer(String idDealer, String idAdmin) {
        try {
            // Ottieni una connessione al database
            Connection conn = DatabaseUtil.getConnection();

            try {
                // Query SQL per disabilitare o abilitare un negoziante
                String query = "UPDATE user SET abilitato = CASE WHEN abilitato = 0 THEN 1 ELSE 0 END WHERE idUser = ? AND tipo = ?";
                PreparedStatement stmt = conn.prepareStatement(query);

                try {
                    // Imposta i parametri nella query
                    stmt.setString(1, idDealer);
                    stmt.setString(2, "dealer");

                    int rs = stmt.executeUpdate();

                    if (rs > 0) {
                        // Verifica lo stato di abilitazione del negoziante dopo l'operazione
                        String query2 = "SELECT abilitato FROM user WHERE idUser = ? AND tipo = ?";
                        PreparedStatement stmt2 = conn.prepareStatement(query2);

                        try {
                            stmt2.setString(1, idDealer);
                            stmt2.setString(2, "dealer");
                            ResultSet rs2 = stmt2.executeQuery();

                            try {
                                if (rs2.next()) {
                                    Integer valore = rs2.getInt("abilitato");
                                    String operazioneDescrizione = "";

                                    // Determina la descrizione dell'operazione
                                    if (valore == 1) {
                                        operazioneDescrizione = "Abilita negoziante";
                                    } else {
                                        operazioneDescrizione = "Disabilita negoziante";
                                    }

                                    // Registra l'operazione effettuata dall'amministratore
                                    query = "INSERT INTO operazionesuperiore (operatore, tipologia, beneficiario, dataOra) VALUES (?,?,?,?)";
                                    PreparedStatement stmt3 = conn.prepareStatement(query);

                                    try {
                                        stmt3.setString(1, idAdmin);
                                        stmt3.setString(2, operazioneDescrizione);
                                        stmt3.setString(3, idDealer);
                                        stmt3.setString(4, LocalDateTime.now().toString());
                                        int rs3 = stmt3.executeUpdate();

                                        if (rs3 > 0) {
                                            // Restituisci un messaggio di conferma
                                            if (valore == 1) {
                                                String s = "Negoziante abilitato.";
                                                return s;
                                            } else {
                                                String s2 = "Negoziante disabilitato.";
                                                return s2;
                                            }
                                        }
                                    }
                                    catch (SQLException e) {
                                        e.printStackTrace();
                                    }
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
        return "Id negoziante non valido.";
    }
}
