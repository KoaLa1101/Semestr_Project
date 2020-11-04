package ru.ITLab.services;

import ru.ITLab.modules.Post;

import java.util.List;
import java.util.Optional;

public interface GetPostService {
    List<Post> getAllPosts(Long user_id);
    Optional<Post> getLastPost(Long user_id);
}
