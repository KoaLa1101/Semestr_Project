package ru.ITLab.services;

import ru.ITLab.modules.User;
import ru.ITLab.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService{
    private UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<User> getUserInfo(Long id) {
        if(usersRepository.findById(id).isPresent()){
            User user = usersRepository.findById(id).get();
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = usersRepository.findAll();
        return allUsers;
    }

}
