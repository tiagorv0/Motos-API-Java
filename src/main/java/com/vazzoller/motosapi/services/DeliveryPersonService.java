package com.vazzoller.motosapi.services;

import com.vazzoller.motosapi.domain.model.DeliveryPerson;
import com.vazzoller.motosapi.domain.repository.DeliveryPersonRepository;
import com.vazzoller.motosapi.services.dtos.deliveryPerson.DeliveryPersonInputDTO;
import com.vazzoller.motosapi.services.dtos.deliveryPerson.DeliveryPersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPersonService {

    @Autowired
    private DeliveryPersonRepository deliveryPersonRepository;

    public DeliveryPersonResponse createDeliveryPerson(DeliveryPersonInputDTO dto){

        DeliveryPerson deliveryPerson = dto.toEntity();

        deliveryPersonRepository.save(deliveryPerson);

        return new DeliveryPersonResponse().fromEntity(deliveryPerson);
    }
}
