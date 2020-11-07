package ru.ITLab.dto;

import lombok.Data;

@Data
public class CreateCommentForm {
    private Long id;
    private Long user_id;
    private Long post_id;
    private String nameHost;
    private String text;
    private String postName;
}
