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
import java.util.prefs.BackingStoreException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UserService userService;
    private String BASE_CONTEXT;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user;
        if (req.getSession(false) != null && req.getSession(false).getAttribute("id") != null) {
            user = userService.getUserInfo((Long) req.getSession(false).getAttribute("id"));
            if (user.isPresent()) {
                HttpSession session = req.getSession(false);
                session.setAttribute("firstName", user.get().getFirstName());
                session.setAttribute("lastName", user.get().getLastName());
                session.setAttribute("email", user.get().getEmail());
                session.setAttribute("dateOfBirth", user.get().getDateOfBirth());
                req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
            }
            else{
                resp.sendRedirect(BASE_CONTEXT);
            }
        }
        else{
            resp.sendRedirect(BASE_CONTEXT);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(BASE_CONTEXT);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = (UserService) config.getServletContext().getAttribute("userService");
        BASE_CONTEXT = (String) config.getServletContext().getAttribute("baseContext");
    }
}
