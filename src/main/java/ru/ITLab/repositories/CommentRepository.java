package ru.ITLab.repositories;

import ru.ITLab.modules.Comment;

import java.util.Optional;

public interface CommentRepository extends CrudRepository {
    Optional<Comment> findByPostName(String postName);
}
