package ru.ITLab.repositories;

import ru.ITLab.modules.Comment;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentRepositoryJdbcImpl implements CommentRepository {

    private DataSource dataSource;

    //language-SQL
    private final static String SQL_INSERT = "insert into comments(user_id, post_id, nameHost, text, namePost) values(?, ?, ?, ?, ?)";
    private final static String SQL_GET_ALL = "select * from comments";
    private final static String SQL_GET_COMMENT_BY_ID = "select * from comments where id=?";
    private final static String SQL_GET_COMMENT_BY_POSTNAME = "select * from comments where postName=?";
    private final static String SQL_GET_COMMENT_BY_USER_ID = "select  * from commments where user_id=?";
    private final static String SQL_DELETE_COMMENT = "delete from comments where id=?";

    public CommentRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Comment> getByPostName(String postName) {
        Connection connection = null;
        PreparedStatement statement = null;
        Optional<Comment> commentByPostName = Optional.empty();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_COMMENT_BY_POSTNAME);
            statement.setString(1, "postName");

            try (ResultSet isCommentByPostName = statement.executeQuery()){
                while (isCommentByPostName.next()) {
                    Comment comment = Comment.builder().id(isCommentByPostName.getLong("id"))
                            .namePost(isCommentByPostName.getString("namePost"))
                            .post_id(isCommentByPostName.getLong("post_id"))
                            .user_id(isCommentByPostName.getLong("user_id"))
                            .nameHost(isCommentByPostName.getString("nameHost"))
                            .text(isCommentByPostName.getString("text")).build();
                    commentByPostName = Optional.of(comment);
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }
        return commentByPostName;
    }

    @Override
    public Optional<Comment> getByUserId(Long user_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Optional<Comment> commentByUserId = Optional.empty();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_COMMENT_BY_USER_ID);
            statement.setLong(1, user_id);

            try (ResultSet isCommentByUserId = statement.executeQuery()){
                while (isCommentByUserId.next()) {
                    Comment comment = Comment.builder().id(isCommentByUserId.getLong("id"))
                            .namePost(isCommentByUserId.getString("namePost"))
                            .post_id(isCommentByUserId.getLong("post_id"))
                            .user_id(isCommentByUserId.getLong("user_id"))
                            .nameHost(isCommentByUserId.getString("nameHost"))
                            .text(isCommentByUserId.getString("text")).build();
                    commentByUserId = Optional.of(comment);
                }
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException throwables) {
            }
        }
        return commentByUserId;
    }

    @Override
    public List findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Comment> allComments = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_ALL);

            try (ResultSet setOfAllComments = statement.executeQuery()) {
                while (setOfAllComments.next()) {
                    Comment comment = Comment.builder().id(setOfAllComments.getLong("id"))
                            .namePost(setOfAllComments.getString("namePost"))
                            .post_id(setOfAllComments.getLong("post_id"))
                            .user_id(setOfAllComments.getLong("user_id"))
                            .nameHost(setOfAllComments.getString("nameHost"))
                            .text(setOfAllComments.getString("text")).build();
                    allComments.add(comment);
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
        return allComments;
    }

    @Override
    public Optional findById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Optional<Comment> commentById = Optional.empty();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GET_COMMENT_BY_ID);
            statement.setLong(1, id);

            try (ResultSet isCommentById = statement.executeQuery()) {
                if (isCommentById.next()) {
                    Comment comment = Comment.builder().id(isCommentById.getLong("id"))
                            .namePost(isCommentById.getString("namePost"))
                            .post_id(isCommentById.getLong("post_id"))
                            .user_id(isCommentById.getLong("user_id"))
                            .nameHost(isCommentById.getString("nameHost"))
                            .text(isCommentById.getString("text")).build();
                    commentById = Optional.of(comment);
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

        return commentById;
    }

    @Override
    public void save(Comment enity) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setLong(1,enity.getUser_id());
            statement.setLong(2, enity.getPost_id());
            statement.setString(3, enity.getNameHost());
            statement.setString(4, enity.getText());
            statement.setString(5, enity.getNamePost());

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Problem with insert comment");
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
    public void update(Comment enity) {

    }

    @Override
    public void deleteById(Long id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_COMMENT, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Problem with delete comment");
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
    public void delete(Comment enity) {
        deleteById(enity.getId());

    }
}
