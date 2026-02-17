package com.vazzoller.motosapi.services.dtos.rental;

import com.vazzoller.motosapi.domain.enums.PlanEnum;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RentalInputDTO {
    @NotNull
    private UUID motoId;
    @NotNull
    private UUID deliveryPersonId;
    @NotNull
    private PlanEnum plan;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    @NotNull
    private LocalDateTime otherEndDate;
}
