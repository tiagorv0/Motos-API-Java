package com.vazzoller.motosapi.services;

import com.vazzoller.motosapi.domain.exception.ConflictException;
import com.vazzoller.motosapi.domain.exception.ResourceNotFoundException;
import com.vazzoller.motosapi.domain.model.Motorcycle;
import com.vazzoller.motosapi.domain.repository.MotorcycleRepository;
import com.vazzoller.motosapi.domain.specification.MotorcycleSpecification;
import com.vazzoller.motosapi.messaging.producer.MessageProducer;
import com.vazzoller.motosapi.services.dtos.motorcycle.MotorcycleInputDTO;
import com.vazzoller.motosapi.services.dtos.motorcycle.MotorcycleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AdminMotorcycleService {

    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private MessageProducer messageProducer;

    public MotorcycleResponse createMotorcycle(MotorcycleInputDTO dto){

        if(motorcycleRepository.existsByPlateAndActiveTrue(dto.getPlate())){
            throw new ConflictException("Já existe uma moto cadastrada com a placa " + dto.getPlate());
        }
        var motorcycle = new Motorcycle(dto.getIdentifier(), dto.getYear(), dto.getModel(), dto.getPlate());
        motorcycleRepository.save(motorcycle);

        if (Integer.valueOf(2024).equals(dto.getYear())) {
            messageProducer.send("motorcycle.year.2024.created", new MotorcycleResponse().toResponse(motorcycle));
        }

        return new MotorcycleResponse().toResponse(motorcycle);
    }

    @Transactional(readOnly = true)
    public Page<MotorcycleResponse> getMotorcyclesPageable(Pageable pageable, String plate){
        Specification<Motorcycle> spec = MotorcycleSpecification.findPlate(plate);

        Page<Motorcycle> motorcycles = motorcycleRepository.findAll(spec, pageable);

        return motorcycles.map(x -> new MotorcycleResponse().toResponse(x));
    }

    @Transactional(readOnly = true)
    public MotorcycleResponse getMotorcycleById(UUID id){
        Motorcycle motorcycle = motorcycleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada com o id:" + id));

        return new MotorcycleResponse().toResponse(motorcycle);
    }

    public void deleteMotorcycleById(UUID id){
        Motorcycle motorcycle = motorcycleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada com o id:" + id));

        motorcycleRepository.delete(motorcycle);
    }

    public MotorcycleResponse patchPlate(UUID id, String plate){
        if(motorcycleRepository.existsByPlateAndActiveTrue(plate)){
            throw new ConflictException("Já existe uma moto cadastrada com a placa " + plate);
        }

        Motorcycle motorcycle = motorcycleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto não encontrada com placa:" + plate));

        motorcycle.setPlate(plate);
        motorcycleRepository.save(motorcycle);

        return new MotorcycleResponse().toResponse(motorcycle);
    }
}
