package com.vazzoller.motosapi.api.controller;

import com.vazzoller.motosapi.services.RentalService;
import com.vazzoller.motosapi.services.dtos.rental.RentalInputDTO;
import com.vazzoller.motosapi.services.dtos.rental.RentalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController("rental")
@RequestMapping("/api/locacoes")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok(rentalService.getById(id));
    }

    @PostMapping
    public ResponseEntity<RentalResponse> create(@RequestBody RentalInputDTO dto){
        return ResponseEntity.ok(rentalService.createRental(dto));
    }

    @PutMapping("/{id}/devolucao")
    public ResponseEntity<RentalResponse> put(@PathVariable UUID id, @RequestBody LocalDate devolutionDate){
        return ResponseEntity.ok(rentalService.finalizeRental(id, devolutionDate));
    }
}
