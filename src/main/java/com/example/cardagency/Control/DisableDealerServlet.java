package com.example.cardagency.Control;

import com.example.cardagency.Model.EnableDisable;
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
        name = "disableDealerServlet",
        value = {"/disableDealer-servlet"}
)
public class DisableDealerServlet extends HttpServlet {
    private String message;

    public DisableDealerServlet() {
    }

    public void init() {
        this.message = "Hello DisableDealer!";
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
        String idDealer = request.getParameter("idDealer");
        HttpSession session = request.getSession();
        String idAdmin = session.getAttribute("idUser").toString();
        if(idDealer != null && idAdmin != null) {
            String esito = EnableDisable.disableDealer(idDealer, idAdmin);
            request.setAttribute("comunicazione", esito);
            String destination = "Views/adminArea.jsp";
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