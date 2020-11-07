package ru.ITLab.servlets;

import org.json.JSONArray;
import ru.ITLab.modules.Post;
import ru.ITLab.repositories.PostRepository;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/showPost")
public class ShowPostServlet extends HttpServlet {
    private GetPostService getPostService;
    private String BASE_CONTEXT;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(BASE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long id = (Long) session.getAttribute("id");
        List<Post> allPostsByUser = getPostService.getAllPosts(id);
        JSONArray arr1 = new JSONArray();

        for(Post post : allPostsByUser) {
            JSONArray arr = new JSONArray();
            arr.put(post.getId());
            arr.put(post.getName());
            arr.put(post.getComment_id());
            arr.put(post.getUser_id());
            arr.put(post.getNameHost());
            arr.put(post.getText());

            arr1.put(arr);
        }

        PrintWriter out = new PrintWriter(new OutputStreamWriter(
                resp.getOutputStream(), StandardCharsets.UTF_8), true);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        out.write(arr1 + "");
        out.flush();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        BASE_CONTEXT = (String) config.getServletContext().getAttribute("baseContext");
        getPostService = (GetPostService) config.getServletContext().getAttribute("getPostService");
    }
}
