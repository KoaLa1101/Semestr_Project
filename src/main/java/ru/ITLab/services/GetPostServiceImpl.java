package ru.ITLab.services;

import ru.ITLab.modules.Post;
import ru.ITLab.repositories.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GetPostServiceImpl implements GetPostService {
    private PostRepository postRepository;

    public GetPostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts(Long user_id) {
        return postRepository.findAllByUser(user_id);
    }

    @Override
    public Optional<Post> getLastPost(Long user_id) {
        List<Post> allPost = postRepository.findAllByUser(user_id);
        return Optional.of(allPost.get(allPost.size() - 1));
    }
}
