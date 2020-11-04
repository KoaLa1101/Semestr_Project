package ru.ITLab.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signOut")
public class SignOutServlet extends HttpServlet {
    private String BASE_CONTEXT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession(false).getAttribute("id") != null){
            req.getSession(false).setAttribute("id", null);
        }
        resp.sendRedirect(BASE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.sendRedirect(BASE_CONTEXT);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        BASE_CONTEXT = (String) config.getServletContext().getAttribute("baseContext");
    }
}
