package ru.ITLab.services;

import ru.ITLab.modules.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserInfo(Long id);
    //TODO:
}
