package ru.ITLab.servlets;

import ru.ITLab.dto.SignUpForm;
import ru.ITLab.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private SignUpService signUpService;
    private String BASE_CONTEXT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(BASE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm form = new SignUpForm();
        form.setFirstName(req.getParameter("firstName"));
        form.setLastName(req.getParameter("lastName"));
        form.setEmail(req.getParameter("email"));
        form.setPassword(req.getParameter("password"));

        int year = Integer.parseInt(req.getParameter("dateOfBirth").split("-")[0]);
        short month = Short.parseShort(req.getParameter("dateOfBirth").split("-")[1]);
        short day = Short.parseShort(req.getParameter("dateOfBirth").split("-")[2]);
        form.setDateOfBirth(new Date(year, month, day));

        Long id = signUpService.signUp(form);
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
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");
        BASE_CONTEXT = (String) config.getServletContext().getAttribute("baseContext");
    }
}
