package com.vazzoller.motosapi.domain.enums;

import lombok.Getter;

@Getter
public enum PlanEnum {
    SEVEN_DAYS((short)7, 30.00),
    FIFTEEN_DAYS((short)15, 28.00),
    THIRD_DAYS((short)30, 22.00),
    FORTY_FIVE_DAYS((short)45, 20.00),
    FIFTY_DAYS((short)50, 18.00);

    private short days;
    private Double price;

    PlanEnum(short days, Double price){
        this.days = days;
        this.price = price;
    }
}
