package com.vazzoller.motosapi.api.controller;

import com.vazzoller.motosapi.services.DeliveryPersonService;
import com.vazzoller.motosapi.services.dtos.deliveryPerson.DeliveryPersonInputDTO;
import com.vazzoller.motosapi.services.dtos.deliveryPerson.DeliveryPersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entregadores")
public class DeliveryPersonController {

    @Autowired
    private DeliveryPersonService deliveryPersonService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<DeliveryPersonResponse> create(@RequestParam DeliveryPersonInputDTO dto){
        return ResponseEntity.ok(deliveryPersonService.createDeliveryPerson(dto));
    }
}
