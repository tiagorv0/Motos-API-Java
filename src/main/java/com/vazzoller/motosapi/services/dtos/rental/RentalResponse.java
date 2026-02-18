package com.vazzoller.motosapi.services.dtos.rental;

import com.vazzoller.motosapi.domain.enums.PlanEnum;
import com.vazzoller.motosapi.domain.model.Rentals;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
public class RentalResponse {
    private UUID motoId;
    private UUID deliveryPersonId;
    private PlanEnum plan;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate otherEndDate;
    private LocalDate devolutionDate;
    private BigDecimal totalCost;
    private BigDecimal dailyCost;
    private BigDecimal penaltyCost;
    private BigDecimal additionalDaysCost;
    private boolean active;

    public RentalResponse fromEntity(Rentals rental){
        this.motoId = rental.getMotorcycle().getId();
        this.deliveryPersonId = rental.getDeliveryPerson().getId();
        this.plan = rental.getPlan();
        this.startDate = rental.getStartDate();
        this.endDate = rental.getEndDate();
        this.otherEndDate = rental.getOtherEndDate();
        this.active = rental.getActive();
        this.devolutionDate = rental.getDevolutionDate();
        this.totalCost = rental.getTotalCost();
        this.dailyCost = rental.getDailyCost();
        this.penaltyCost = rental.getPenaltyCost();
        this.additionalDaysCost = rental.getAdditionalDaysCost();

        return this;
    }
}
