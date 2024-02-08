package com.buelna.springsecurity.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ApiErrorResponse implements Serializable {

    private String backendMessage;

    private String message;

    private String url;

    private String method;

    private LocalDateTime timestamp;
}
