package ru.ITLab.repositories;

import ru.ITLab.modules.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment> {
    List<Comment> getByPostName(String postName);
    List<Comment> getByUserId(Long user_id);
}
