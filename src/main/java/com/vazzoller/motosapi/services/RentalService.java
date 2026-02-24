package com.vazzoller.motosapi.services;

import com.vazzoller.motosapi.domain.exception.ResourceNotFoundException;
import com.vazzoller.motosapi.domain.model.DeliveryPerson;
import com.vazzoller.motosapi.domain.model.Motorcycle;
import com.vazzoller.motosapi.domain.model.Rentals;
import com.vazzoller.motosapi.domain.repository.DeliveryPersonRepository;
import com.vazzoller.motosapi.domain.repository.MotorcycleRepository;
import com.vazzoller.motosapi.domain.repository.RentalsRepository;
import com.vazzoller.motosapi.services.dtos.rental.RentalInputDTO;
import com.vazzoller.motosapi.services.dtos.rental.RentalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class RentalService {

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Transactional(readOnly = true)
    public RentalResponse getById(UUID id){
        Rentals rental = rentalsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locação não encontrada"));

        return new RentalResponse().fromEntity(rental);
    }

    @Transactional
    public RentalResponse createRental(RentalInputDTO dto){
        DeliveryPerson deliveryPerson = deliveryPersonRepository.findById(dto.getDeliveryPersonId())
                .orElseThrow(() -> new ResourceNotFoundException("Entregador não encontrado"));

        Motorcycle motorcycle = motorcycleRepository.findById(dto.getMotoId())
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada"));

        Rentals rental = dto.toEntity(motorcycle, deliveryPerson);

        rentalsRepository.save(rental);
        deliveryPersonRepository.save(deliveryPerson);
        motorcycleRepository.save(motorcycle);

        return new RentalResponse().fromEntity(rental);
    }

    public RentalResponse finalizeRental(UUID id, LocalDate devolutionDate){
        Rentals rental = rentalsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locação não encontrada"));

        rental.calculateRental(devolutionDate);

        rentalsRepository.save(rental);

        return new RentalResponse().fromEntity(rental);
    }
}
