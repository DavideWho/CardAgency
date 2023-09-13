package com.example.cardagency.Control;

import com.example.cardagency.Model.History;
import com.example.cardagency.Util.Filtro;
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
        name = "reportAdminServlet",
        value = {"/reportAdmin-servlet"}
)
public class ReportAdminServlet extends HttpServlet {
    private String message;

    public ReportAdminServlet() {
    }

    public void init() {
        this.message = "Hello ReportAdmin!";
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
        String idAdmin = request.getParameter("idAdmin");
        String filtro1 = request.getParameter("filter1");
        String filtro2 = request.getParameter("filter2");
        String filtro3 = request.getParameter("filter3");
        Filtro filtro = new Filtro(filtro1, filtro2, filtro3);
        ArrayList list;
        if(idAdmin != null) {
            if (filtro1 == null && filtro2 == null && filtro3 == null) {
                list = History.getReport(idAdmin, 2);
            } else {
                list = History.getReport(idAdmin, 2, filtro);
            }

            request.setAttribute("operazioni", list);
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
