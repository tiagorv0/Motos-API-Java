package com.vazzoller.motosapi.services.dtos.rental;

import com.vazzoller.motosapi.domain.enums.PlanEnum;
import com.vazzoller.motosapi.domain.model.DeliveryPerson;
import com.vazzoller.motosapi.domain.model.Motorcycle;
import com.vazzoller.motosapi.domain.model.Rentals;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class RentalInputDTO {
    @NotNull
    private UUID motoId;
    @NotNull
    private UUID deliveryPersonId;
    @NotNull
    private short plan;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private LocalDate otherEndDate;

    public Rentals toEntity(Motorcycle motorcycle, DeliveryPerson deliveryPerson){
        return new Rentals(startDate, endDate, otherEndDate, PlanEnum.fromShort(plan), motorcycle, deliveryPerson);
    }
}
