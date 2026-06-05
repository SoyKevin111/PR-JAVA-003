package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank(message = "campo obligatorio") String username,
    @NotBlank(message = "campo obligatorio") String password) {

}
