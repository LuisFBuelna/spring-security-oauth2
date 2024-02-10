package com.buelna.springsecurity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisteredUserdto implements Serializable {

    private Long id;

    private String username;

    private String name;

    private String role;

    private String jwt;
}
