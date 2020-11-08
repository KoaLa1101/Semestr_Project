package ru.ITLab.services;

import ru.ITLab.dto.CreateCommentForm;

public interface CreateCommentService {
    Long createComment(CreateCommentForm form);
}
