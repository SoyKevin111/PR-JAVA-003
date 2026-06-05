package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "nombre no puede ser vacío")
    private String nombre;

    @NotNull(message = "edad no puede ser nula")
    @Min(value = 0, message = "edad no puede ser negativa")
    private Integer edad;

    @NotBlank(message = "username no puede ser vacío")
    private String username;

    @NotBlank(message = "password no puede ser vacío")
    private String password;
}
