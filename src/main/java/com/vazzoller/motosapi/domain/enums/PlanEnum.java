package com.vazzoller.motosapi.domain.enums;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum PlanEnum {
    SEVEN_DAYS((short)7, new BigDecimal("30.00"), new BigDecimal("0.20")),
    FIFTEEN_DAYS((short)15, new BigDecimal("28.00"), new BigDecimal("0.40")),
    THIRTY_DAYS((short)30, new BigDecimal("22.00"), BigDecimal.ZERO),
    FORTY_FIVE_DAYS((short)45, new BigDecimal("20.00"), BigDecimal.ZERO),
    FIFTY_DAYS((short)50, new BigDecimal("18.00"), BigDecimal.ZERO);

    private final short days;
    private final BigDecimal price;
    private final BigDecimal penaltyRate;

    PlanEnum(short days, BigDecimal price, BigDecimal penaltyRate) {
        this.days = days;
        this.price = price;
        this.penaltyRate = penaltyRate;
    }

    public static PlanEnum fromShort(short value){
        for (PlanEnum s : PlanEnum.values()) {
            if (s.getDays() == value) {
                return s;
            }
        }

        throw new IllegalArgumentException("Código inválido: " + value);
    }
}
