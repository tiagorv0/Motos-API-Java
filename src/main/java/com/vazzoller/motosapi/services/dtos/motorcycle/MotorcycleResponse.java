package com.vazzoller.motosapi.services.dtos.motorcycle;

import com.vazzoller.motosapi.domain.model.Motorcycle;
import lombok.Getter;

import java.util.UUID;

@Getter
public class MotorcycleResponse {
    private String identifier;
    private Integer year;
    private String model;
    private String plate;
    private UUID id;
    private boolean active;

    public MotorcycleResponse toResponse(Motorcycle motorcycle){
        id = motorcycle.getId();
        active = motorcycle.getActive();
        plate = motorcycle.getPlate();
        model = motorcycle.getModel();
        year = motorcycle.getYear();
        identifier = motorcycle.getIdentifier();

        return this;
    }
}
