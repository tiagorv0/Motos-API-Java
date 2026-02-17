package com.vazzoller.motosapi.services.dtos.rental;

import com.vazzoller.motosapi.domain.enums.PlanEnum;
import com.vazzoller.motosapi.domain.model.Rentals;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class RentalResponse {
    private UUID motoId;
    private UUID deliveryPersonId;
    private PlanEnum plan;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime otherEndDate;
    private boolean active;

    public RentalResponse fromEntity(Rentals rental){
        this.motoId = rental.getMotorcycle().getId();
        this.deliveryPersonId = rental.getDeliveryPerson().getId();
        this.plan = rental.getPlan();
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.otherEndDate = rental.getOtherEndDate();
        this.active = rental.getActive();

        return this;
    }
}
