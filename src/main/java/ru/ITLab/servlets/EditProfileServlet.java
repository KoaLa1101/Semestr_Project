package ru.ITLab.servlets;

import ru.ITLab.dto.EditProfileForm;
import ru.ITLab.repositories.UsersRepository;
import ru.ITLab.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editProfile")
public class EditProfileServlet extends HttpServlet {
    private UserService userService;
    private String BASE_CONTEXT;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(BASE_CONTEXT);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditProfileForm form = new EditProfileForm();
        form.setFirstName(req.getParameter("newFirstName"));
        form.setLastName(req.getParameter("newLastName"));
        form.setId((Long) req.getSession().getAttribute("id"));
        userService.changeProfile(form);
        resp.sendRedirect(BASE_CONTEXT + "profile");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        BASE_CONTEXT = (String) config.getServletContext().getAttribute("baseContext");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }
}
