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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@WebServlet("/myPosts")
public class MyPostsServlet extends HttpServlet {
    private GetPostService getPostService;
    private UserService userService;
    private String BASE_CONTEXT;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user;
        if (req.getSession(false) != null && req.getSession(false).getAttribute("id") != null) {
            user = userService.getUserInfo((Long) req.getSession(false).getAttribute("id"));
            if (user.isPresent()) {
                req.getRequestDispatcher("/WEB-INF/allMyPosts.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(BASE_CONTEXT + "sign_in");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Post> allPosts = getPostService.getAllPostsByUser((Long) session.getAttribute("id"));
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
