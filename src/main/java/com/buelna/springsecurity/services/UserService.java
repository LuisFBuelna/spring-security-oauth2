package com.buelna.springsecurity.services;

import com.buelna.springsecurity.dto.SaveUserDto;
import com.buelna.springsecurity.entities.User;

public interface UserService {
    User registerOneCustomer(SaveUserDto newUser);
}
