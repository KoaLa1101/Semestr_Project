package ru.ITLab.services;

import ru.ITLab.dto.CreateCommentForm;
import ru.ITLab.modules.Comment;
import ru.ITLab.repositories.CommentRepository;

public class CreateCommentServiceImpl implements CreateCommentService {
    private CommentRepository commentRepository;

    public CreateCommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
    @Override
    public Long createCommentService(CreateCommentForm form) {
        Long id = form.getId();

        if(!commentRepository.findById(id).isPresent()){
            Comment comment = Comment.builder()
                    .nameHost(form.getNameHost())
                    .namePost(form.getPostName())
                    .post_id(form.getPost_id())
                    .user_id(form.getUser_id())
                    .text(form.getText()).build();
            commentRepository.save(comment);
            return comment.getId();
        }
        else{
            return (long) -4;
        }
    }
}
