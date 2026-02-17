package com.vazzoller.motosapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Motorcycles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorcycle extends BaseEntity {

    @Column(nullable = false)
    @NotBlank
    private String identifier;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    @NotBlank
    private String model;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String plate;

    @OneToMany(mappedBy = "motorcycle")
    private List<Rentals> rentals;

    public Motorcycle(String identifier, Integer year, String model, String plate) {
        this.identifier = identifier;
        this.year = year;
        this.model = model;
        this.plate = plate;
    }
}
