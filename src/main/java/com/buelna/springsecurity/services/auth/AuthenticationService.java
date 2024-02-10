package com.buelna.springsecurity.services.auth;

import com.buelna.springsecurity.dto.RegisteredUserdto;
import com.buelna.springsecurity.dto.SaveUserDto;
import com.buelna.springsecurity.entities.User;
import com.buelna.springsecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    public RegisteredUserdto registerOneCustomer(SaveUserDto newUser) {

        User user = userService.registerOneCustomer(newUser);

        RegisteredUserdto userDto = new RegisteredUserdto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDto.setJwt(jwt);

        return userDto;
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }
}
