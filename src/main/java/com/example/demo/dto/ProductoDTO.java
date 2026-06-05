package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "nombre no puede ser vacío")
    private String nombre;

    @NotBlank(message = "descripcion no puede ser vacía")
    private String descripcion;

    @NotNull(message = "precio no puede ser nulo")
    @Positive(message = "precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "stock no puede ser nulo")
    @PositiveOrZero(message = "stock no puede ser negativo")
    private Integer stock;
}
