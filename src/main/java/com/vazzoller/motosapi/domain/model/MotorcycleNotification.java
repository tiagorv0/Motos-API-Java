package com.vazzoller.motosapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "motorcycle_notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotorcycleNotification extends BaseEntity{
    @Column(name = "motorcycle_id", nullable = false)
    private UUID motorcycleId;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "model", nullable = false, length = 100)
    private String model;

    @Column(name = "plate", nullable = false, length = 20)
    private String plate;
}
