package ru.ITLab.services;

import ru.ITLab.dto.CreateCommentForm;

public interface CreateCommentService {
    Long createCommentService(CreateCommentForm form);
}
