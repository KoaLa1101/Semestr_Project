package ru.ITLab.servlets;

import org.json.JSONArray;
import ru.ITLab.modules.Post;
import ru.ITLab.modules.User;
import ru.ITLab.services.GetPostService;
import ru.ITLab.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@WebServlet("/forum")
public class ForumServlet extends HttpServlet {
    private GetPostService getPostService;
    private String BASE_CONTEXT;
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user;
        if (req.getSession(false) != null && req.getSession(false).getAttribute("id") != null) {
            user = userService.getUserInfo((Long) req.getSession(false).getAttribute("id"));
            if (user.isPresent()) {
                req.getRequestDispatcher("/forum.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(BASE_CONTEXT + "sign_in");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Post> allPosts = getPostService.getAllPosts();
        JSONArray arr1 = new JSONArray();

        for (Post post : allPosts) {
            JSONArray arr = new JSONArray();
            arr.put(post.getName());
            arr.put(post.getText());
            arr.put(post.getId());
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
        userService = (UserService) config.getServletContext().getAttribute("userService");

    }
}
