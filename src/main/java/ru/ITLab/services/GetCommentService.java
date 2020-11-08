package ru.ITLab.services;

import ru.ITLab.modules.Comment;

import java.util.List;

public interface GetCommentService {
    List<Comment> allCommentByPost(String name);
}
