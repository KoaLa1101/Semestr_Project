package ru.ITLab.servlets;

import ru.ITLab.dto.CreateCommentForm;
import ru.ITLab.modules.Post;
import ru.ITLab.services.CreateCommentService;
import ru.ITLab.services.GetPostService;

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
    private GetPostService getPostService;
    private PostServlet postServlet;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(BASE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateCommentForm form = new CreateCommentForm();
        HttpSession session = req.getSession();
        form.setUser_id((Long) session.getAttribute("id"));
        form.setNameHost((String) session.getAttribute("firstName"));
        form.setPost_id((Long) session.getAttribute("post_id"));
        form.setPostName((getPostService.getById(form.getPost_id()).get().getName()));
        form.setText(req.getParameter("text"));
        Long id = createCommentService.createComment(form);
        String path = req.getContextPath();
        resp.sendRedirect(BASE_CONTEXT + "forum/postId?id=" + req.getParameter("id"));

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        BASE_CONTEXT = (String) config.getServletContext().getAttribute("baseContext");
        createCommentService = (CreateCommentService) config.getServletContext().getAttribute("createCommentService");
        getPostService = (GetPostService) config.getServletContext().getAttribute("getPostService");
        postServlet = new PostServlet();
    }
}
