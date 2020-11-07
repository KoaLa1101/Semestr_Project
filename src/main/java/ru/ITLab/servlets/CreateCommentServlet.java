package ru.ITLab.servlets;

import ru.ITLab.dto.CreateCommentForm;
import ru.ITLab.services.CreateCommentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createComment")
public class CreateCommentServlet extends HttpServlet {
    private String BASE_CONTEXT;
    private CreateCommentService createCommentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(BASE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateCommentForm form = new CreateCommentForm();
        HttpSession session = req.getSession();

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        BASE_CONTEXT = (String) config.getServletContext().getAttribute("baseContext");
        createCommentService = (CreateCommentService) config.getServletContext().getAttribute("createCommentService");
    }
}
