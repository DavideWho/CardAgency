package com.example.cardagency.Control;

import com.example.cardagency.Model.CardOperations;
import com.example.cardagency.Util.RiscontroOperazione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet(
        name = "cardOperationsServlet",
        value = {"/cardOperations-servlet"}
)
public class CardOperationsServlet extends HttpServlet {
    private String message;

    public CardOperationsServlet() {
    }

    public void init() {
        this.message = "Hello Card Operations!";
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
     * @throws IOException      Eccezione lanciata se si verifica un errore di I/O durante la gestione della richiesta.
     * @throws ServletException Eccezione lanciata se si verifica un errore durante l'inoltro della richiesta.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        System.out.println("SONO NELLA CARDOPERATIONSERVLET");
        HttpSession session = request.getSession();
        String nCard = request.getParameter("nCard");
        String amount = request.getParameter("amount");
        String operation = request.getParameter("operation");
        String operatore = session.getAttribute("idUser").toString();
        RiscontroOperazione x = null;
        if (nCard != null && amount != null && operation != null && operatore != null){
            x = CardOperations.applyOperation(nCard, operation, amount, operatore);
            request.setAttribute("stato", x.getStato());
            String destination = "/Views/dealerArea.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
            requestDispatcher.forward(request, response);
        }else {
            out.println("<script>");
            out.println("alert('Campi non validi.');");
            out.println("window.history.back();");
            out.println("</script>");
        }
    }
}
