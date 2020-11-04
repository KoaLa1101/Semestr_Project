package ru.ITLab.services;

import ru.ITLab.dto.CreatePostForm;

public interface CreatePostService {
    Long createPost(CreatePostForm createPostForm);
}
