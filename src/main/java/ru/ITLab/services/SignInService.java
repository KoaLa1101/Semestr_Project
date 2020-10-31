package ru.ITLab.services;

import ru.ITLab.dto.SignInForm;

public interface SignInService {
    Long signIn(SignInForm form);
}
