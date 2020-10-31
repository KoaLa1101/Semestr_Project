package ru.ITLab.repositories;

import ru.ITLab.modules.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findFirstByFirstNameAndLastName( String firstName, String lastName);
    Optional<User> getUserByEmail(String email);
}
