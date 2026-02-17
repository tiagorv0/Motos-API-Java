package com.vazzoller.motosapi.services;

import com.vazzoller.motosapi.domain.exception.ResourceNotFoundException;
import com.vazzoller.motosapi.domain.model.Rentals;
import com.vazzoller.motosapi.domain.repository.RentalsRepository;
import com.vazzoller.motosapi.services.dtos.rental.RentalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RentalService {

    @Autowired
    private RentalsRepository rentalsRepository;

    @Transactional(readOnly = true)
    public RentalResponse getById(UUID id){
        Rentals rental = rentalsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locação não encontrada"));

        return new RentalResponse().fromEntity(rental);
    }
}
