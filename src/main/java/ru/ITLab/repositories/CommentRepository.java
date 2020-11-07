package ru.ITLab.repositories;

import ru.ITLab.modules.Comment;

import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment> {
    Optional<Comment> getByPostName(String postName);
    Optional<Comment> getByUserId(Long user_id);
}
