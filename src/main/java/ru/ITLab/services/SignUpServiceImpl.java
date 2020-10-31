package ru.ITLab.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ITLab.dto.SignUpForm;
import ru.ITLab.modules.User;
import ru.ITLab.repositories.UsersRepository;

public class SignUpServiceImpl implements SignUpService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Long signUp(SignUpForm form) {
        String email = form.getEmail();

        if(!usersRepository.getUserByEmail(email).isPresent()) {
            User user = User.builder()
                    .firstName(form.getFirstName())
                    .lastName(form.getLastName())
                    .email(form.getEmail())
                    .dateOfBirth(form.getDateOfBirth())
                    .hashPassword(passwordEncoder.encode(form.getPassword()))
                    .build();
            usersRepository.save(user);
            return usersRepository.getUserByEmail(email).get().getId();
        } else {
            return (long) -1;
        }
    }
}
