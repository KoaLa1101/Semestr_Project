package ru.ITLab.repositories;

import ru.ITLab.modules.Comment;
import ru.ITLab.modules.Post;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private final static String SQL_DELETE_COMMENT = "delete from comments where id=?";

    public CommentRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Comment> findByPostName(String postName) {
        return Optional.empty();
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
        return Optional.empty();
    }

    @Override
    public void save(Object enity) {

    }

    @Override
    public void update(Object enity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Object enity) {

    }
}
