package com.example.cardagency.Control;

import com.example.cardagency.Model.Delete;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet(
        name = "deleteAccountServlet",
        value = {"/deleteAccount-servlet"}
)
public class DeleteAccountServlet extends HttpServlet {
    private String message;

    public DeleteAccountServlet() {
    }

    public void init() {
        this.message = "Hello DeleteAccount!";
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
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name2");
        String surname = request.getParameter("surname2");
        String email = request.getParameter("email2");
        String psw = request.getParameter("hashedPsw");
        System.out.println(name + " " + surname + " " + email + " " + psw);
        String flag = null;
        if(name != null && surname != null && email != null && psw != null) {
            flag = Delete.deleteAccount(name, surname, email, psw);
            String destination = "Views/dealerArea.jsp";
            request.setAttribute("flag", flag);
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
