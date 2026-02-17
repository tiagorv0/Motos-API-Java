package com.vazzoller.motosapi.services.dtos.deliveryPerson;

import com.vazzoller.motosapi.domain.enums.DriversLicenseTypeEnum;
import com.vazzoller.motosapi.domain.model.DeliveryPerson;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class DeliveryPersonResponse {
    private String identifier;
    private String name;
    private String cnpj;
    private LocalDate birthDate;
    private Long cnhNumber;
    private DriversLicenseTypeEnum licenseType;
    private String cnhImageUrl;
    private UUID id;
    private boolean active;
    private LocalDateTime createdDate;

    public  DeliveryPersonResponse fromEntity(DeliveryPerson entity){
        identifier = entity.getIdentifier();
        name = entity.getName();
        cnpj = entity.getCnpj();
        birthDate = entity.getBirthDate();
        cnhNumber = entity.getCnhNumber();
        licenseType = entity.getLicenseType();
        cnhImageUrl = entity.getCnhImageUrl();
        id = entity.getId();
        active = entity.getActive();
        createdDate = entity.getCreatedDate();

        return this;
    }
}
