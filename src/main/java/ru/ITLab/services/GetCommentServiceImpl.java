package ru.ITLab.services;

import ru.ITLab.modules.Comment;
import ru.ITLab.repositories.CommentRepository;

import java.util.List;

public class GetCommentServiceImpl implements GetCommentService {
    private CommentRepository commentRepository;

    public GetCommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> allCommentByPost(String name) {
        return commentRepository.getByPostName(name);
    }
}
