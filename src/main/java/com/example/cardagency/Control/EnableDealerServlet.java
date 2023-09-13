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
        name = "enableServlet",
        value = {"/enable-servlet"}
)
public class EnableDealerServlet extends HttpServlet {
    private String message;

    public EnableDealerServlet() {
    }

    public void init() {
        this.message = "Hello EnableDealer!";
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
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String psw = request.getParameter("hashedPsw");
        HttpSession session = request.getSession();
        String idAdmin = session.getAttribute("idUser").toString();
        String esito = EnableDisable.enableDealer(name, surname, email, psw, idAdmin);
        if(esito != null){
            request.setAttribute("news", esito);
            String destination = "Views/adminArea.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(destination);
            requestDispatcher.forward(request, response);
        }else{
            out.println("<script>");
            out.println("alert('Campi non validi.');");
            out.println("window.history.back();");
            out.println("</script>");
        }
    }
}
