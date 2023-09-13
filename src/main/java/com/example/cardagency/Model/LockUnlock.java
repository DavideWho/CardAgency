package com.example.cardagency.Model;

import com.example.cardagency.Util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class LockUnlock {

    /**
     * Blocca una carta specifica impostando il suo stato su "bloccato".
     *
     * @param nCard     Il numero di carta da bloccare.
     * @param operatore L'operatore che esegue l'azione di blocco.
     * @return Una stringa che indica l'esito dell'operazione di blocco.
     */
    public static String lock(String nCard, String operatore) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            try {
                String query = "UPDATE card SET status = 1 WHERE nCard = ?";
                try {
                    PreparedStatement stmt = conn.prepareStatement(query);
                    try {
                        stmt.setString(1, nCard);
                        int rs = stmt.executeUpdate();
                        if (rs > 0) {
                            String ex = signOp(nCard, 1, operatore);
                            String s = ex;
                            return s;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Carta non trovata";
    }

    /**
     * Sblocca una carta specifica impostando il suo stato su "non bloccato".
     *
     * @param nCard     Il numero di carta da sbloccare.
     * @param operatore L'operatore che esegue l'azione di sblocco.
     * @return Una stringa che indica l'esito dell'operazione di sblocco.
     */
    public static String unlock(String nCard, String operatore) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            try {
                String query = "UPDATE card SET status = 0 WHERE nCard = ?";
                try {
                    PreparedStatement stmt = conn.prepareStatement(query);
                    try {
                        stmt.setString(1, nCard);
                        int rs = stmt.executeUpdate();
                        if (rs > 0) {
                            String ex = signOp(nCard, 2, operatore);
                            String s = ex;
                            return s;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Carta non trovata";
    }

    /**
     * Registra un'operazione di blocco o sblocco di una carta nel database.
     *
     * @param nCard     Il numero di carta su cui è stata eseguita l'operazione.
     * @param type      Il tipo di operazione (1 per blocco, 2 per sblocco).
     * @param operatore L'operatore che ha eseguito l'operazione.
     * @return Una stringa che indica l'esito dell'operazione di registrazione.
     */
    public static String signOp(String nCard, Integer type, String operatore) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            try {
                String query = "INSERT INTO operazione (nCard, tipologia, operatore, dataOra) VALUES (?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                try {
                    stmt.setString(1, nCard);
                    if (type == 1) {
                        stmt.setString(2, "bloccoCarta");
                    } else if (type == 2) {
                        stmt.setString(2, "sbloccoCarta");
                    }
                    stmt.setString(3, operatore);
                    stmt.setString(4, LocalDateTime.now().toString());
                    int rs = stmt.executeUpdate();
                    if (type == 1) {
                        String s = "Carta bloccata";
                        return s;
                    }
                    String s2 = "Carta sbloccata";
                    return s2;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("<h1>Error accessing the database</h1>");
            return "Carta non trovata";
        }
        return "Carta non trovata";
    }
}
