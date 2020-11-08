package ru.ITLab.services;

import ru.ITLab.modules.Post;

import java.util.List;
import java.util.Optional;

public interface GetPostService {
    List<Post> getAllPostsByUser(Long user_id);
    Optional<Post> getLastPost(Long user_id);
    List<Post> getAllPosts();
    Optional<Post> findByName(String name);
    Optional<Post> getById(Long id);
}
