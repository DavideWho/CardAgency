package com.example.cardagency.Model;

import com.example.cardagency.Util.DatabaseUtil;
import com.example.cardagency.Util.Filtro;
import com.example.cardagency.Util.Operazione;
import com.example.cardagency.Util.OperazioneGenerica;
import com.example.cardagency.Util.OperazioneSuperiore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class History {

    /**
     * Ottiene la cronologia delle operazioni per una determinata carta.
     *
     * @param nCard Il numero di carta per il quale ottenere la cronologia.
     * @return Una lista di oggetti Operazione che rappresentano la cronologia delle operazioni.
     */
    public static ArrayList<Operazione> getHistory(String nCard) {
        ArrayList<Operazione> operazioni = new ArrayList<Operazione>();
        try {
            Connection conn = DatabaseUtil.getConnection();
            try {
                String query = "SELECT idOperazione, tipologia, importo, operatore, dataOra FROM operazione WHERE nCard = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                try {
                    stmt.setString(1, nCard);
                    ResultSet rs = stmt.executeQuery();
                    try {
                        while (rs.next()) {
                            String idOperazione = rs.getString("idOperazione");
                            String tipologia = rs.getString("tipologia");
                            String importo;
                            if (rs.getString("importo") != null) {
                                importo = rs.getString("importo");
                            } else {
                                importo = "NULL";
                            }
                            String operatore = rs.getString("operatore");
                            String dataOra = rs.getString("dataOra");
                            Operazione op = new Operazione(idOperazione, operatore, tipologia, dataOra, importo, nCard);
                            operazioni.add(op);
                        }
                        ArrayList<Operazione> list = operazioni;
                        return list;
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
            Operazione op2 = new Operazione("NULL", "NULL", "NULL", "NULL", "NULL", "NULL");
            operazioni.add(op2);
            return operazioni;
        }
        return operazioni;
    }

    /**
     * Ottiene un report delle operazioni effettuate da un utente specifico.
     *
     * @param idUser L'ID dell'utente per il quale ottenere il report.
     * @param t      Tipo di report (1 per operazioni complete, 2 per operazioni superiori).
     * @return Una lista di oggetti OperazioneGenerica che rappresentano il report delle operazioni.
     */
    public static ArrayList<OperazioneGenerica> getReport(String idUser, Integer t) {
        ArrayList<OperazioneGenerica> operazioni = new ArrayList<OperazioneGenerica>();
        try {
            Connection conn = DatabaseUtil.getConnection();
            try {
                String query = "SELECT idOperazione, tipologia, importo, nCard, dataOra FROM operazione WHERE operatore = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                try {
                    stmt.setString(1, idUser);
                    ResultSet rs = stmt.executeQuery();
                    try {
                        while (rs.next()) {
                            String idOperazione = rs.getString("idOperazione");
                            String tipologia = rs.getString("tipologia");
                            String importo = "";
                            if (t == 1) {
                                importo = rs.getString("importo");
                            } else if (t == 2) {
                                importo = "NULL";
                            }
                            String nCard = rs.getString("nCard");
                            String dataOra = rs.getString("dataOra");
                            Operazione op = new Operazione(idOperazione, idUser, tipologia, dataOra, importo, nCard);
                            operazioni.add((OperazioneGenerica) op);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (t == 2) {
                    String query2 = "SELECT idOperazioneSuperiore, operatore, tipologia, beneficiario, dataOra FROM operazionesuperiore WHERE operatore = ?";
                    PreparedStatement stmt2 = conn.prepareStatement(query2);
                    try {
                        stmt2.setString(1, idUser);
                        ResultSet rs2 = stmt2.executeQuery();
                        try {
                            while (rs2.next()) {
                                String idOperazione2 = rs2.getString("idOperazioneSuperiore");
                                String operatore = rs2.getString("operatore");
                                String tipologia2 = rs2.getString("tipologia");
                                String beneficiario = rs2.getString("beneficiario");
                                String dataOra2 = rs2.getString("dataOra");
                                OperazioneSuperiore op2 = new OperazioneSuperiore(idOperazione2, operatore, tipologia2, dataOra2, beneficiario);
                                operazioni.add((OperazioneGenerica) op2);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                ArrayList<OperazioneGenerica> list = operazioni;
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return operazioni;
        }
        return operazioni;
    }

    /**
     * Ottiene un report delle operazioni effettuate da un utente specifico con filtri opzionali.
     *
     * @param idUser L'ID dell'utente per il quale ottenere il report.
     * @param t      Tipo di report (1 per operazioni complete, 2 per operazioni superiori).
     * @param filtro Un oggetto Filtro che contiene i filtri opzionali per la query.
     * @return Una lista di oggetti OperazioneGenerica che rappresentano il report delle operazioni filtrate.
     */
    public static ArrayList<OperazioneGenerica> getReport(String idUser, Integer t, Filtro filtro) {
        ArrayList<OperazioneGenerica> operazioni = new ArrayList<OperazioneGenerica>();
        try {
            Connection conn = DatabaseUtil.getConnection();
            try {
                String query;
                if (filtro.getFiltro1().equals("") && filtro.getFiltro3().equals("")) {
                    query = "SELECT idOperazione, tipologia, importo, nCard, dataOra FROM operazione WHERE operatore = ?";
                } else if (filtro.getFiltro3().equals("")) {
                    query = "SELECT idOperazione, tipologia, importo, nCard, dataOra FROM operazione WHERE operatore = ? AND tipologia = ?";
                } else if (filtro.getFiltro1().equals("")) {
                    query = "SELECT idOperazione, tipologia, importo, nCard, dataOra FROM operazione WHERE operatore = ? AND dataORA LIKE ?";
                } else {
                    query = "SELECT idOperazione, tipologia, importo, nCard, dataOra FROM operazione WHERE operatore = ? AND tipologia = ? AND dataORA LIKE ?";
                }
                PreparedStatement stmt = conn.prepareStatement(query);
                try {
                    stmt.setString(1, idUser);
                    if (filtro.getFiltro3().equals("")) {
                        stmt.setString(2, filtro.getFiltro1());
                    } else if (filtro.equals("")) {
                        stmt.setString(2, "%" + filtro.getFiltro3() + "%");
                    } else {
                        stmt.setString(2, filtro.getFiltro1());
                        stmt.setString(3, "%" + filtro.getFiltro3() + "%");
                    }
                    ResultSet rs = stmt.executeQuery();
                    try {
                        while (rs.next()) {
                            String idOperazione = rs.getString("idOperazione");
                            String tipologia = rs.getString("tipologia");
                            String importo = "";
                            if (t == 1) {
                                importo = rs.getString("importo");
                            } else if (t == 2) {
                                importo = "NULL";
                            }
                            String nCard = rs.getString("nCard");
                            String dataOra = rs.getString("dataOra");
                            Operazione op = new Operazione(idOperazione, idUser, tipologia, dataOra, importo, nCard);
                            operazioni.add((OperazioneGenerica) op);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (t == 2) {
                    String query2;
                    if (filtro.getFiltro2().equals("") && filtro.getFiltro3().equals("")) {
                        query2 = "SELECT idOperazioneSuperiore, operatore, tipologia, beneficiario, dataOra FROM operazionesuperiore WHERE operatore = ?";
                    } else if (filtro.getFiltro3().equals("")) {
                        query2 = "SELECT idOperazioneSuperiore, operatore, tipologia, beneficiario, dataOra FROM operazionesuperiore WHERE operatore = ? AND tipologia = ?";
                    } else if (filtro.getFiltro2().equals("")) {
                        query2 = "SELECT idOperazioneSuperiore, operatore, tipologia, beneficiario, dataOra FROM operazionesuperiore WHERE operatore = ? AND dataOra LIKE ?";
                    } else {
                        query2 = "SELECT idOperazioneSuperiore, operatore, tipologia, beneficiario, dataOra FROM operazionesuperiore WHERE operatore = ? AND tipologia = ? AND dataOra LIKE ?";
                    }
                    try {
                        PreparedStatement stmt2 = conn.prepareStatement(query2);
                        try {
                            stmt2.setString(1, idUser);
                            if (filtro.getFiltro3().equals("")) {
                                stmt2.setString(2, filtro.getFiltro2());
                            } else if (filtro.getFiltro2().equals("")) {
                                stmt2.setString(2, "%" + filtro.getFiltro3() + "%");
                            } else {
                                stmt2.setString(2, filtro.getFiltro2());
                                stmt2.setString(3, "%" + filtro.getFiltro3() + "%");
                            }
                            ResultSet rs2 = stmt2.executeQuery();
                            try {
                                while (rs2.next()) {
                                    String idOperazione2 = rs2.getString("idOperazioneSuperiore");
                                    String operatore = rs2.getString("operatore");
                                    String tipologia2 = rs2.getString("tipologia");
                                    String beneficiario = rs2.getString("beneficiario");
                                    String dataOra2 = rs2.getString("dataOra");
                                    OperazioneSuperiore op2 = new OperazioneSuperiore(idOperazione2, operatore, tipologia2, dataOra2, beneficiario);
                                    operazioni.add(op2);
                                }
                                ArrayList<OperazioneGenerica> list = operazioni;
                                return list;
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                ArrayList<OperazioneGenerica> list2 = operazioni;
                return list2;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
            return operazioni;
        }
        return operazioni;
    }
}
