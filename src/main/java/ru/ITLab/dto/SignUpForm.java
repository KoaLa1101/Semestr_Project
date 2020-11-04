package ru.ITLab.dto;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;

@Data
public class SignUpForm {
    private String email;
    private Date dateOfBirth;
    private String password;
    private String firstName;
    private String lastName;
}
