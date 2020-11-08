package ru.ITLab.servlets;

import org.json.JSONArray;
import ru.ITLab.modules.Comment;
import ru.ITLab.modules.Post;
import ru.ITLab.services.GetCommentService;
import ru.ITLab.services.GetPostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/forum/postId")
public class PostServlet extends HttpServlet {
    private String BASE_CONTEXT;
    private GetPostService getPostService;
    private GetCommentService getCommentService;
    private String name;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("post_id", Long.parseLong(req.getParameter("id")));
        name = getPostService.getById(Long.parseLong(req.getParameter("id"))).get().getName();
        req.getRequestDispatcher("/WEB-INF/post.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> allPosts = getPostService.getAllPosts();
        List<Comment> allCommentByPost = getCommentService.allCommentByPost(name);
        JSONArray arr1 = new JSONArray();
        JSONArray arr2 = new JSONArray();

        for(Post post : allPosts) {
            JSONArray arr = new JSONArray();
            arr.put(post.getName());
            arr.put(post.getText());
            arr.put(post.getId());
            arr1.put(arr);
        }

        for(Comment comment : allCommentByPost){
            JSONArray arr = new JSONArray();
            arr.put(comment.getNameHost());
            arr.put(comment.getText());
            arr.put(comment.getId());
            arr2.put(arr);
        }

        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8), true);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        out.write(arr1 + "&&" + arr2);
        out.flush();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        BASE_CONTEXT = (String) config.getServletContext().getAttribute("baseContext");
        getPostService = (GetPostService) config.getServletContext().getAttribute("getPostService");
        getCommentService = (GetCommentService) config.getServletContext().getAttribute("getCommentService");
    }
}
