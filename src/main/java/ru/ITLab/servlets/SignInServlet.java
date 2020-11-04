package ru.ITLab.servlets;

import ru.ITLab.dto.SignInForm;
import ru.ITLab.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;
    private String BASE_CONTEXT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(BASE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInForm form = new SignInForm();
        form.setEmail(req.getParameter("email"));
        form.setPassword(req.getParameter("password"));

        Long id = signInService.signIn(form);
        if (id >= 0) {
            HttpSession session = req.getSession();
            session.setAttribute("id", id);
            resp.sendRedirect(BASE_CONTEXT + "profile");
        } else {
            req.getSession().setAttribute("error", id);
            resp.sendRedirect(BASE_CONTEXT);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
        BASE_CONTEXT = (String) config.getServletContext().getAttribute("baseContext");
    }
}
