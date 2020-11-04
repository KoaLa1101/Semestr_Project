package ru.ITLab.repositories;

import ru.ITLab.modules.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post> {
    Optional<Post> findById(Long id);
    Optional<Post> findByName(String name);
    List<Post> findAllByUser(Long user_id);
}
