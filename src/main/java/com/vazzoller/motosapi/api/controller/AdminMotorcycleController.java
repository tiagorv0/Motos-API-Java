package com.vazzoller.motosapi.api.controller;

import com.vazzoller.motosapi.services.AdminMotorcycleService;
import com.vazzoller.motosapi.services.dtos.motorcycle.MotorcycleInputDTO;
import com.vazzoller.motosapi.services.dtos.motorcycle.MotorcycleResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/motos")
public class AdminMotorcycleController {

    @Autowired
    private AdminMotorcycleService adminMotorcycleService;

    @PostMapping
    public ResponseEntity<MotorcycleResponse> createMotorcycle(@Valid @RequestBody MotorcycleInputDTO dto){
        MotorcycleResponse response = adminMotorcycleService.createMotorcycle(dto);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("update-plate/{id}")
    public ResponseEntity<MotorcycleResponse> patchPlate(@PathVariable UUID id, @RequestBody String plate){
        MotorcycleResponse response = adminMotorcycleService.patchPlate(id, plate);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<MotorcycleResponse>> getMotorcyclesPageable(@RequestParam(required = false) String plate, @ParameterObject @PageableDefault(size = 15, page = 0) Pageable pageable){
        Page<MotorcycleResponse> response = adminMotorcycleService.getMotorcyclesPageable(pageable, plate);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotorcycleResponse> getById(@PathVariable UUID id){
        return ResponseEntity.ok(adminMotorcycleService.getMotorcycleById(id));
    }

    @DeleteMapping
    public void removeById(@PathVariable UUID id){
        adminMotorcycleService.deleteMotorcycleById(id);
    }
}
