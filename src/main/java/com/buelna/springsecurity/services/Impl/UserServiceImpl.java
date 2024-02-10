package com.buelna.springsecurity.services.Impl;

import com.buelna.springsecurity.dto.SaveUserDto;
import com.buelna.springsecurity.entities.User;
import com.buelna.springsecurity.entities.util.Role;
import com.buelna.springsecurity.exception.InvalidPasswordException;
import com.buelna.springsecurity.repositories.UserRepository;
import com.buelna.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerOneCustomer(SaveUserDto newUser) {

        validatePassword(newUser);

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setName(newUser.getName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);

        return userRepository.save(user);
    }

    private void validatePassword(SaveUserDto newUser) {

        if (!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatedPassword())) {
            throw new InvalidPasswordException("El password no es valido");
        }
        if (!newUser.getPassword().equals(newUser.getRepeatedPassword())) {
            throw new InvalidPasswordException("Los passwords no hacen match");
        }
    }
}
