package com.example.cardagency.Control;

import com.example.cardagency.Model.Login;
import com.example.cardagency.Util.Autenticazione;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(
        name = "loginServlet",
        value = {"/login-servlet"}
)
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String message;

    public LoginServlet() {
    }

    public void init() {
        this.message = "Hello Login!";
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
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String psw = request.getParameter("hashedPsw");
        Autenticazione x = Login.authenticateUser(email, psw);
        if (x != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("idUser", x.getIdUser());
            session.setAttribute("name", x.getName());
            session.setAttribute("surname", x.getSurname());
            session.setAttribute("tipo", x.getType());
            switch ((String)session.getAttribute("tipo")) {
                case "cliente":
                    response.sendRedirect(request.getContextPath() + "/Views/personalArea.jsp");
                    break;
                case "dealer":
                    response.sendRedirect(request.getContextPath() + "/Views/dealerArea.jsp");
                    break;
                case "admin":
                    response.sendRedirect(request.getContextPath() + "/Views/adminArea.jsp");
            }
        } else {
            out.println("<script>");
            out.println("alert('Credenziali non valide.');");
            out.println("window.history.back();");
            out.println("</script>");
        }

    }

}
