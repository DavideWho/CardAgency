package com.example.cardagency.Control;

import com.example.cardagency.Model.History;
import com.example.cardagency.Util.Operazione;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static java.lang.System.out;

@WebServlet(
        name = "historyServlet",
        value = {"/history-servlet"}
)
public class HistoryServlet extends HttpServlet {
    private String message;

    public HistoryServlet() {
    }

    public void init() {
        this.message = "Hello History!";
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
        String nCard = request.getParameter("nCard");
        ArrayList<Operazione> list;
        if(nCard != null) {
            list = History.getHistory(nCard);
            request.setAttribute("operazioni", list);
            //System.out.println("PROVA REQUEST IN SERVLET: " + request.getAttribute("operazioni").toString());
            String destination = "Views/cardArea.jsp";
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
