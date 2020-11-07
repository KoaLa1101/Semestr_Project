package ru.ITLab.dto;

import lombok.Data;

@Data
public class CreatePostForm {
    private Long id;
    private String name;
    private Long user_id;
    private Long[] comment_id;
    private String nameHost;
    private String text;
}
