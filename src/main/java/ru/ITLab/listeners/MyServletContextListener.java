package ru.ITLab.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ITLab.dto.CreatePostForm;
import ru.ITLab.repositories.PostRepository;
import ru.ITLab.repositories.PostRepositoryJdbcImpl;
import ru.ITLab.repositories.UsersRepository;
import ru.ITLab.repositories.UsersRepositoryJdbcImpl;
import ru.ITLab.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestr_project";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Qwert123";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String BASE_CONTEXT = "http://localhost:8180/";


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        PostRepository postRepository = new PostRepositoryJdbcImpl(dataSource);
        SignInService signInService = new SignInServiceImpl(usersRepository);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository);
        UserService userService = new UserServiceImpl(usersRepository);
        CreatePostService createPostService = new CreatePostServiceImpl(postRepository);
        GetPostService getPostService = new GetPostServiceImpl(postRepository);

        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("baseContext", BASE_CONTEXT);
        servletContext.setAttribute("createPostService", createPostService);
        servletContext.setAttribute("getPostService", getPostService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
