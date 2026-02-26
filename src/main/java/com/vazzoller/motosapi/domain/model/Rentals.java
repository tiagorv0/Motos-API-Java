package com.vazzoller.motosapi.domain.model;

import com.vazzoller.motosapi.domain.enums.DriversLicenseTypeEnum;
import com.vazzoller.motosapi.domain.enums.PlanEnum;
import com.vazzoller.motosapi.domain.exception.BusinessException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Getter
@NoArgsConstructor
public class Rentals extends BaseEntity {
    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private LocalDate otherEndDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlanEnum plan;

    @Column
    private BigDecimal totalCost;

    @Column
    private BigDecimal dailyCost;

    @Column
    private BigDecimal penaltyCost;

    @Column
    private BigDecimal additionalDaysCost;

    @Column
    private LocalDate devolutionDate;

    @ManyToOne
    @JoinColumn(name = "motorcycle_id")
    private Motorcycle motorcycle;

    @ManyToOne
    @JoinColumn(name = "delivery_person_id")
    private DeliveryPerson deliveryPerson;

    public Rentals(LocalDate startDate, LocalDate endDate, LocalDate otherEndDate, PlanEnum plan, Motorcycle motorcycle, DeliveryPerson deliverPerson){
        if(deliverPerson.getLicenseType().equals(DriversLicenseTypeEnum.B)){
            throw new BusinessException("Moto não pode ser alugada para entregadores com CNH B");
        }

        this.startDate = startDate;
        this.endDate = endDate;
        this.otherEndDate = otherEndDate;
        this.plan = plan;
        this.motorcycle = motorcycle;
        this.deliveryPerson = deliverPerson;
    }

    public void calculateRental(LocalDate devolutionDate){

        if(devolutionDate.isBefore(startDate)){
            throw new BusinessException("A data de devolução não pode ser anterior à data de início da locação");
        }

        this.devolutionDate = devolutionDate;

        this.calculateDailyCost();

        if(devolutionDate.isBefore(endDate)) {
            this.calculatePenaltyCost();
            return;
        }

        if(devolutionDate.isAfter(otherEndDate)) {
            this.calculateAdditionalValue();
            return;
        }

        totalCost = dailyCost;
    }

    private void calculateDailyCost(){
        LocalDate firstDay = startDate.plusDays(1);

        int days = firstDay.until(devolutionDate).getDays();

        this.dailyCost = plan.getPrice().multiply(new BigDecimal(days));
    }

    private void calculateAdditionalValue(){
        int days = otherEndDate.until(devolutionDate).getDays();

        BigDecimal additionalValuePerDay = new BigDecimal("50.00");
        this.additionalDaysCost = additionalValuePerDay.multiply(new BigDecimal(days));
        this.totalCost = dailyCost.add(this.additionalDaysCost);

    }

    private void calculatePenaltyCost(){
        this.penaltyCost = dailyCost.multiply(plan.getPenaltyRate());
        this.totalCost = dailyCost.add(this.penaltyCost);
    }
}
