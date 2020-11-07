package ru.ITLab.servlets;

import org.json.JSONArray;
import ru.ITLab.modules.Post;
import ru.ITLab.modules.User;
import ru.ITLab.repositories.UsersRepository;
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

@WebServlet("/members")
public class MembersServlet extends HttpServlet {
    private String BASE_CONTEXT;
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/members.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = userService.getAllUsers();
        JSONArray arr1 = new JSONArray();

        for(User user : allUsers) {
            JSONArray arr = new JSONArray();
            arr.put(user.getFirstName());
            arr.put(user.getLastName());
            arr.put(user.getEmail());
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
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }
}
