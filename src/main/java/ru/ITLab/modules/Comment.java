package ru.ITLab.modules;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Comment {
    private Long id;
    private Long user_id;
    private Long post_id;
    private String nameHost;
    private String text;
    private String postName;
}
