package com.example.cardagency.Model;

import com.example.cardagency.Util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewCard {

    /**
     * Invia una nuova carta per un utente specifico.
     *
     * @param idUser L'identificatore dell'utente a cui verrà associata la nuova carta.
     * @return Il numero della nuova carta (nCard) se l'operazione ha successo, altrimenti null.
     */
    public static Long sendNewCard(String idUser) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            try {
                String query = "SELECT nCard FROM card";
                PreparedStatement stmt3 = conn.prepareStatement(query);
                try {
                    ResultSet rs3 = stmt3.executeQuery();
                    try {
                        List<Long> cards = new ArrayList<Long>();
                        while (rs3.next()) {
                            cards.add(Long.valueOf(rs3.getString("nCard")));
                        }
                        Long newNcard = Collections.max(cards) + 1L;
                        query = "INSERT INTO card (nCard, idProprietario) VALUES (?,?)";
                        try (PreparedStatement stmt4 = conn.prepareStatement(query)) {
                            stmt4.setString(1, newNcard.toString());
                            stmt4.setString(2, idUser);
                            int rs4 = stmt4.executeUpdate();
                            if (rs4 > 0) {
                                // Restituisce il numero della nuova carta (nCard) se l'operazione ha successo
                                Long n = newNcard;
                                return n;
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
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("<h1>Error accessing the database</h1>");
        }
        return null;
    }
}