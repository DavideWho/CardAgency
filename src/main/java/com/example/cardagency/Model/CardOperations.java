/**
 * Questa classe gestisce le operazioni sulle carte.
 */
package com.example.cardagency.Model;

import com.example.cardagency.Util.DatabaseUtil;
import com.example.cardagency.Util.RiscontroOperazione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CardOperations {

    /**
     * Applica un'operazione (accredito o addebito) a una carta.
     *
     * @param nCard     Il numero della carta su cui eseguire l'operazione.
     * @param operation Tipo di operazione (accredito o addebito).
     * @param amount    L'importo dell'operazione.
     * @param operatore Il nome dell'operatore che esegue l'operazione.
     * @return Un oggetto RiscontroOperazione che rappresenta l'esito dell'operazione.
     */
    public static RiscontroOperazione applyOperation(String nCard, String operation, String amount, String operatore) {
        RiscontroOperazione x = new RiscontroOperazione();

        try {
            // Ottieni una connessione al database
            Connection conn = DatabaseUtil.getConnection();

            try {
                // Cerca lo stato della carta nel database
                String query = "SELECT status FROM card WHERE nCard = ?";
                PreparedStatement stmt = conn.prepareStatement(query);

                try {
                    stmt.setString(1, nCard);
                    ResultSet rs = stmt.executeQuery();

                    try {
                        if (rs.next()) {
                            String status = rs.getString("status");

                            // Se lo stato della carta non è "0", restituisci il risultato corrente
                            if (!status.equals("0")) {
                                RiscontroOperazione riscontroOperazione = x;
                                return riscontroOperazione;
                            }

                            // Se l'operazione è di accredito, aggiorna il saldo e registra l'operazione
                            if (operation.equals("accredito")) {
                                query = "UPDATE card SET saldo = saldo + ? WHERE nCard = ?";
                                PreparedStatement stmt2 = conn.prepareStatement(query);

                                try {
                                    stmt2.setString(1, amount);
                                    stmt2.setString(2, nCard);
                                    int rs2 = stmt2.executeUpdate();
                                    if (rs2 > 0)
                                        x.setStato("1");
                                }
                                catch (SQLException e) {
                                    e.printStackTrace();
                                }

                                query = "INSERT INTO operazione (nCard, tipologia, importo, operatore, dataOra) VALUES (?,?,?,?,?)";
                                stmt2 = conn.prepareStatement(query);

                                try {
                                    stmt2.setString(1, nCard);
                                    stmt2.setString(2, "accredito");
                                    stmt2.setString(3, amount);
                                    stmt2.setString(4, operatore);
                                    stmt2.setString(5, LocalDateTime.now().toString());
                                    int rs2 = stmt2.executeUpdate();
                                }
                                catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            // Se l'operazione è di addebito, verifica il saldo e registra l'operazione
                            else {
                                query = "SELECT saldo FROM card WHERE nCard = ?";
                                PreparedStatement stmt3 = conn.prepareStatement(query);

                                try {
                                    stmt3.setString(1, nCard);
                                    ResultSet rs3 = stmt3.executeQuery();

                                    try {
                                        if (rs3.next()) {
                                            float saldoDisponibile = Float.valueOf(rs3.getString("saldo"));

                                            if (saldoDisponibile >= Float.valueOf(amount)) {
                                                query = "UPDATE card SET saldo = saldo - ? WHERE nCard = ?";
                                                PreparedStatement stmt4 = conn.prepareStatement(query);

                                                try {
                                                    stmt4.setString(1, amount);
                                                    stmt4.setString(2, nCard);
                                                    int rs4 = stmt4.executeUpdate();

                                                    if (rs4 > 0)
                                                        x.setStato("1");
                                                }
                                                catch (SQLException e) {
                                                    e.printStackTrace();
                                                }

                                                query = "INSERT INTO operazione (nCard, tipologia, importo, operatore, dataOra) VALUES (?,?,?,?,?)";
                                                stmt4 = conn.prepareStatement(query);

                                                try {
                                                    stmt4.setString(1, nCard);
                                                    stmt4.setString(2, "addebito");
                                                    stmt4.setString(3, amount);
                                                    stmt4.setString(4, operatore);
                                                    stmt4.setString(5, LocalDateTime.now().toString());
                                                    int rs4 = stmt4.executeUpdate();
                                                    RiscontroOperazione riscontroOperazione2 = x;
                                                    return riscontroOperazione2;
                                                }
                                                catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            x.setStato("2");
                                            RiscontroOperazione riscontroOperazione3 = x;
                                            return riscontroOperazione3;
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
        return x;
    }
}
