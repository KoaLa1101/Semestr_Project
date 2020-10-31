package ru.ITLab.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.ITLab.repositories.UsersRepository;
import ru.ITLab.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/semestr_project";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Qwert123";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String BASE_CONTEXT = "http://localhost:8180/Semestr_Project_Web_exploded/";



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
