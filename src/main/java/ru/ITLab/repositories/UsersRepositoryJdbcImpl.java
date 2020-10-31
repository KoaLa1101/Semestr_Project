package ru.ITLab.repositories;

import ru.ITLab.modules.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource dataSource;

    //language=SQL
    private final static String SQL_INSERT = "insert into account(email, hpassword, firstName, lastName, age) " +
            "values (?, ?, ?, ?, ?, ?)";
    private final static String SQL_GET_USER_BY_FNANDLN = "select * from users where firstName=? and lastName=?";
    private final static String SQL_GET_ALL = "select * from users";
    private final static String SQL_GET_USER_BY_EMAIL = "select * from users where email=?";
    private final static String SQL_GET_USER_BY_ID = "select * from users where id=?";

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void save(User entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setShort(3, entity.getAge());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getHashPassword());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Problem with insert user");
            }

            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("Problem with retrieve id");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException throwables) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                }
            }
        }
    }


    @Override
    public Optional<User> findFirstByFirstNameAndLastName(String firstName, String lastName) {
        Connection connection = null;
        PreparedStatement statement = null;
        Optional<User> userByFirstNameAndLastName = Optional.empty();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_USER_BY_FNANDLN);
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            try(ResultSet isUserByFirstNameAndLastName = statement.executeQuery()){
                if(isUserByFirstNameAndLastName.next()){
                    User user = User.builder().id(isUserByFirstNameAndLastName.getLong("id"))
                            .firstName(isUserByFirstNameAndLastName.getString("firstName"))
                            .lastName(isUserByFirstNameAndLastName.getString("lastName"))
                            .email(isUserByFirstNameAndLastName.getString("email"))
                            .dateOfBirth(isUserByFirstNameAndLastName.getDate("dateOfBirth"))
                            .hashPassword(isUserByFirstNameAndLastName.getString("hash_password")).build();
                    userByFirstNameAndLastName = Optional.of(user);
                }
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        } finally {
            try {
                if(statement!=null) statement.close();
                if(connection!=null) connection.close();
            } catch (SQLException throwables){
            }
        }
        return userByFirstNameAndLastName;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        Optional<User> userByEmail = Optional.empty();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_USER_BY_EMAIL);
            statement.setString(1, email);

            try (ResultSet isUserByEmail = statement.executeQuery()) {
                if (isUserByEmail.next()) {
                    User user = User.builder().id(isUserByEmail.getLong("id"))
                            .firstName(isUserByEmail.getString("firstName"))
                            .lastName(isUserByEmail.getString("lastName"))
                            .email(isUserByEmail.getString("email"))
                            .dateOfBirth(isUserByEmail.getDate("dateOfBirth"))
                            .hashPassword(isUserByEmail.getString("hash_password")).build();
                    userByEmail = Optional.of(user);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }

        return userByEmail;
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<User> allUsers = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_ALL);

            try (ResultSet setOfAllUsers = statement.executeQuery()) {
                while (setOfAllUsers.next()) {
                    User user = User.builder().id(setOfAllUsers.getLong("id"))
                            .firstName(setOfAllUsers.getString("firstName"))
                            .lastName(setOfAllUsers.getString("lastName"))
                            .email(setOfAllUsers.getString("email"))
                            .dateOfBirth(setOfAllUsers.getDate("dateOfBirth"))
                            .hashPassword(setOfAllUsers.getString("hash_password")).build();
                    allUsers.add(user);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }
        return allUsers;
    }

    @Override
    public Optional<User> findById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Optional<User> userByID = Optional.empty();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_USER_BY_ID);
            statement.setLong(1, id);

            try (ResultSet isUserByID = statement.executeQuery()) {
                if (isUserByID.next()) {
                    User user = User.builder().id(isUserByID.getLong("id"))
                            .firstName(isUserByID.getString("firstName"))
                            .lastName(isUserByID.getString("lastName"))
                            .email(isUserByID.getString("email"))
                            .dateOfBirth(isUserByID.getDate("dateOfBirth"))
                            .hashPassword(isUserByID.getString("hash_password")).build();
                    userByID = Optional.of(user);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }

        return userByID;
    }

    @Override
    public void update(User enity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(User enity) {

    }
}
