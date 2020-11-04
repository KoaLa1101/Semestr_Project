package ru.ITLab.modules;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class Post {
    private Long id;
    private String name;
    private Long user_id;
    private Long comment_id;
    private String nameHost;
    private String text;

}
