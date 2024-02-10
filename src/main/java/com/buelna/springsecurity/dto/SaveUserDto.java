package com.buelna.springsecurity.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class SaveUserDto implements Serializable {

    @Size(min = 4, message = "El name debe contener al menos 4 caracteres")
    private String name;

    @Size(min = 8, message = "El username debe contener al menos 8 caracteres")
    private String username;

    @Size(min = 8, message = "El password debe contener al menos 8 caracteres")
    private String password;

    @Size(min = 8, message = "El password debe contener al menos 8 caracteres")
    private String repeatedPassword;
}
