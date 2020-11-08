package ru.ITLab.servlets;

import ru.ITLab.dto.CreatePostForm;
import ru.ITLab.services.CreatePostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createPost")
public class CreatePostServlet extends HttpServlet {
    private CreatePostService createPostService;
    private String BASE_CONTEXT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(BASE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreatePostForm form = new CreatePostForm();
        HttpSession session = req.getSession();
        form.setUser_id((Long) session.getAttribute("id"));

        form.setName(req.getParameter("name"));
        form.setNameHost((String) session.getAttribute("firstName"));
        form.setComment_id(new Long[]{0L});
        form.setText(req.getParameter("text"));
        Long id = (Long) createPostService.createPost(form);

        if(id>=0){
            resp.sendRedirect(BASE_CONTEXT + "profile");
        }
        else{
            req.getSession().setAttribute("error", id);
            resp.sendRedirect(BASE_CONTEXT);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        createPostService = (CreatePostService) config.getServletContext().getAttribute("createPostService");
        BASE_CONTEXT = (String) config.getServletContext().getAttribute("baseContext");
    }
}
