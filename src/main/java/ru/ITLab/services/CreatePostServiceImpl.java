package ru.ITLab.services;

import ru.ITLab.dto.CreatePostForm;
import ru.ITLab.modules.Post;
import ru.ITLab.repositories.PostRepository;
import ru.ITLab.repositories.UsersRepository;

public class CreatePostServiceImpl implements CreatePostService {
    private PostRepository postRepository;
    private UsersRepository usersRepository;

    public CreatePostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    @Override
    public Long createPost(CreatePostForm form) {
        String name = form.getName();

        if (!postRepository.findByName(name).isPresent()) {
            Post post = Post.builder()
                    .name(form.getName())
                    .user_id(form.getUser_id())
                    .comment_id(form.getComment_id())
                    .nameHost(form.getNameHost())
                    .text(form.getText()).build();
            postRepository.save(post);
            return post.getId();
        } else {
            System.out.println("Я отдал -3");
            return (long) -3;
        }
    }
}
