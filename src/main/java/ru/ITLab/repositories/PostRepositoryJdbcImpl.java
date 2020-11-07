package ru.ITLab.repositories;

import ru.ITLab.modules.Post;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepositoryJdbcImpl implements PostRepository {

    private DataSource dataSource;

    //language-SQL
    private final static String SQL_INSERT = "insert into posts(name, user_id, comment_id, nameHost, text) values(?, ?, ?, ?, ?)";
    private final static String SQL_GET_ALL = "select * from posts";
    private final static String SQL_GET_POST_BY_ID = "select * from posts where id=?";
    private final static String SQL_GET_POST_BY_NAME = "select * from posts where name=?";
    private final static String SQL_DELETE_POST = "delete from posts where id=?";
    private final static String SQL_GET_ALL_POST_BY_USER = "select * from posts where user_id=?";

    public PostRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Post> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Post> allPosts = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_ALL);

            try (ResultSet setOfAllPosts = statement.executeQuery()) {
                while (setOfAllPosts.next()) {
                    Post post = Post.builder().id(setOfAllPosts.getLong("id"))
                            .name(setOfAllPosts.getString("name"))
                            .comment_id((Long[]) setOfAllPosts.getArray("comment_id").getArray())
                            .user_id(setOfAllPosts.getLong("user_id"))
                            .nameHost(setOfAllPosts.getString("nameHost"))
                            .text(setOfAllPosts.getString("text")).build();
                    allPosts.add(post);
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
        return allPosts;
    }

    @Override
    public Optional<Post> findById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Optional<Post> postById = Optional.empty();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_POST_BY_ID);
            statement.setLong(1, id);

            try (ResultSet isPostById = statement.executeQuery()) {
                if (isPostById.next()) {
                    Post post = Post.builder().id(isPostById.getLong("id"))
                            .name(isPostById.getString("name"))
                            .user_id(isPostById.getLong("user_id"))
                            .comment_id((Long[]) isPostById.getArray("comment_id").getArray())
                            .nameHost(isPostById.getString("nameHost"))
                            .text(isPostById.getString("text")).build();
                    postById = Optional.of(post);
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

        return postById;
    }

    @Override
    public Optional<Post> findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        Optional<Post> postByName = Optional.empty();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_POST_BY_NAME);
            statement.setString(1, name);

            try (ResultSet isPostByName = statement.executeQuery()) {
                if (isPostByName.next()) {
                    Post post = Post.builder().id(isPostByName.getLong("id"))
                            .name(isPostByName.getString("name"))
                            .user_id(isPostByName.getLong("user_id"))
                            .comment_id((Long[]) isPostByName.getArray("comment_id").getArray())
                            .nameHost(isPostByName.getString("nameHost"))
                            .text(isPostByName.getString("text")).build();
                    postByName = Optional.of(post);
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

        return postByName;
    }


    @Override
    public void save(Post enity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, enity.getName());
            statement.setLong(2, enity.getUser_id());
            statement.setArray(3, connection.createArrayOf("bigint", enity.getComment_id()));
            statement.setString(4, enity.getNameHost());
            statement.setString(5, enity.getText());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Problem with insert post");
            }

            generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                enity.setId(generatedKeys.getLong("id"));
            } else {
                throw new SQLException("Problem with retrieve id");
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
    }

    @Override
    public void update(Post enity) {

    }

    @Override
    public void deleteById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_POST, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Problem with delete post");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }
    }

    @Override
    public void delete(Post enity) {
        deleteById(enity.getId());
    }

    public List<Post> findAllByUser(Long user_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Post> allPostByUser = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_ALL_POST_BY_USER);
            statement.setLong(1, user_id);

            try (ResultSet setOfAllPostsByUser = statement.executeQuery()) {
                while (setOfAllPostsByUser.next()) {
                    Post post = Post.builder().id(setOfAllPostsByUser.getLong("id"))
                            .name(setOfAllPostsByUser.getString("name"))
                            .comment_id((Long[]) setOfAllPostsByUser.getArray("comment_id").getArray())
                            .user_id(setOfAllPostsByUser.getLong("user_id"))
                            .nameHost(setOfAllPostsByUser.getString("nameHost"))
                            .text(setOfAllPostsByUser.getString("text")).build();
                    allPostByUser.add(post);
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
        return allPostByUser;
    }
}
