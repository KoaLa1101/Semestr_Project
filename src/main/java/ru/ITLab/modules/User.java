package ru.ITLab.modules;

import lombok.*;

import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String linkToImage;
    private Short age;
    private String hashPassword;
    private String email;
    private Date dateOfBirth;

}
