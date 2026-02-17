package com.vazzoller.motosapi.services.dtos.motorcycle;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MotorcycleInputDTO {
    @NotBlank(message = "identificador é obrigatório")
    private String identifier;

    @NotNull
    private Integer year;

    @NotBlank
    private String model;

    @NotBlank
    @Size(max = 8, message = "placa deve ter no máxima 8 caracteres")
    private String plate;
}
