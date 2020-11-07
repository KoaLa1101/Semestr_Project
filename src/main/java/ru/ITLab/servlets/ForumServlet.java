package ru.ITLab.servlets;

import ru.ITLab.modules.User;
import ru.ITLab.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/forum")
public class ForumServlet extends HttpServlet {
    private String BASE_CONTEXT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
