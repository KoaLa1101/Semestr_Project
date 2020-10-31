package ru.ITLab.services;

import ru.ITLab.dto.SignUpForm;

public interface SignUpService {
    Long signUp(SignUpForm form);
}
