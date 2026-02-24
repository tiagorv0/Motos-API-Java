package com.vazzoller.motosapi.domain.model;

import com.vazzoller.motosapi.domain.enums.DriversLicenseTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "DeliveryPersons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryPerson extends BaseEntity{
    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false, unique = true)
    @NotBlank
    private String cnpj;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    private Long cnhNumber;

    @Column(nullable = false)
    private DriversLicenseTypeEnum licenseType;

    @Column()
    private String cnhImageUrl;

    @OneToMany(mappedBy = "deliveryPerson")
    private List<Rentals> rentals;

    public DeliveryPerson(String identifier, String name, String cnpj, LocalDate birthDate, Long cnhNumber, DriversLicenseTypeEnum licenseType){
        this.identifier = identifier;
        this.name = name;
        this.cnpj = cnpj;
        this.birthDate = birthDate;
        this.cnhNumber = cnhNumber;
        this.licenseType = licenseType;
    }
}
