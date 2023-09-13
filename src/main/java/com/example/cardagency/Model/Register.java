package com.example.cardagency.Model;

import com.example.cardagency.Util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {

    /**
     * Invia un nuovo utente al sistema.
     *
     * @param name     Il nome dell'utente da registrare.
     * @param surname  Il cognome dell'utente da registrare.
     * @param email    L'indirizzo email dell'utente da registrare.
     * @param psw      La password dell'utente da registrare.
     * @return L'identificatore (idUser) dell'utente appena registrato se l'operazione ha successo, altrimenti "0".
     */
    public static String sendNewUser(String name, String surname, String email, String psw) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            try {
                String query = "INSERT INTO user (name, surname, email, password, tipo) VALUES (?,?,?,?,?)";
                String query2 = "SELECT idUser FROM user WHERE name = ? AND surname = ? AND email = ? AND password = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                try {
                    PreparedStatement stmt2 = conn.prepareStatement(query2);
                    try {
                        stmt.setString(1, name);
                        stmt.setString(2, surname);
                        stmt.setString(3, email);
                        stmt.setString(4, psw);
                        stmt.setString(5, "cliente");
                        int rs = stmt.executeUpdate();
                        if (rs > 0) {
                            stmt2.setString(1, name);
                            stmt2.setString(2, surname);
                            stmt2.setString(3, email);
                            stmt2.setString(4, psw);
                            ResultSet rs2 = stmt2.executeQuery();
                            try {
                                if (rs2.next()) {
                                    // Restituisce l'identificatore (idUser) dell'utente appena registrato se l'operazione ha successo
                                    String string = rs2.getString("idUser");
                                    return string;
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
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
        return "0";
    }
}
