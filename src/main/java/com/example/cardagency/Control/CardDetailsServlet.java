package com.example.cardagency.Control;

import com.example.cardagency.Model.CardDetails;
import com.example.cardagency.Util.Carta;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "cardDetailsServlet",
        value = {"/cardDetails-servlet"}
)
public class CardDetailsServlet extends HttpServlet {
    private String message;

    public CardDetailsServlet() {
    }

    public void init() {
        this.message = "Hello Card Details!";
    }

    /**
     * Gestisce le richieste GET inviate al servlet.
     *
     * @param request  Oggetto HttpServletRequest che rappresenta la richiesta HTTP.
     * @param response Oggetto HttpServletResponse che rappresenta la risposta HTTP.
     * @throws IOException Eccezione lanciata se si verifica un errore di I/O durante la gestione della richiesta.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + this.message + "</h1>");
        out.println("</body></html>");
    }

    /**
     * Gestisce le richieste POST inviate al servlet.
     *
     * @param request  Oggetto HttpServletRequest che rappresenta la richiesta HTTP.
     * @param response Oggetto HttpServletResponse che rappresenta la risposta HTTP.
     * @throws IOException Eccezione lanciata se si verifica un errore di I/O durante la gestione della richiesta.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String nCard = request.getParameter("nCard");
        System.out.println("NCARD: " + nCard + "idUSER: " + session.getAttribute("idUser"));
        Object idUser = session.getAttribute("idUser");
        Carta x = CardDetails.findCardDetails(nCard, idUser.toString());
        if (x != null) {
            session.setAttribute("nCard", x.getnCard());
            session.setAttribute("status", x.getStatus());
            session.setAttribute("saldo", x.getSaldo());
            response.sendRedirect(request.getContextPath() + "/Views/cardArea.jsp");
        }else {
            out.println("<script>");
            out.println("alert('Numero non valido.');");
            out.println("window.history.back();");
            out.println("</script>");
        }

    }
}
