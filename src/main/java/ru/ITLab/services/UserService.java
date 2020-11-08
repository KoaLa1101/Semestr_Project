package ru.ITLab.services;

import ru.ITLab.dto.EditProfileForm;
import ru.ITLab.modules.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserInfo(Long id);
    List<User> getAllUsers();
    void changeProfile(EditProfileForm form);

}
