package com.vazzoller.motosapi.services.dtos.deliveryPerson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vazzoller.motosapi.domain.enums.DriversLicenseTypeEnum;
import com.vazzoller.motosapi.domain.model.DeliveryPerson;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.io.File;
import java.time.LocalDate;

@Data
public class DeliveryPersonInputDTO {
    @NotBlank
    private String identifier;

    @NotBlank
    private String name;

    @CNPJ
    @NotBlank
    private String cnpj;

    @NotBlank
    @JsonFormat(pattern = "dd-MM-yy")
    private LocalDate birthDate;

    @NotBlank
    @Min(value = 100000000L, message = "Deve ter pelo menos 9 dígitos")
    @Max(value = 999999999L, message = "Deve ter no máximo 9 dígitos")
    private Long cnhNumber;

    @NotBlank
    private DriversLicenseTypeEnum licenseType;

    private File cnhImage;

    public DeliveryPerson toEntity(){
        return new DeliveryPerson(identifier, name, cnpj, birthDate, cnhNumber, licenseType);
    }
}
