package com.vazzoller.motosapi.domain.repository;

import com.vazzoller.motosapi.domain.model.Motorcycle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, UUID>, JpaSpecificationExecutor<Motorcycle> {
    boolean existsByPlateAndActiveTrue(String plate);
    Page<Motorcycle> findByPlateAndActiveTrue(String plate, Pageable pageable);
    Page<Motorcycle> findAllByActiveTrue(Pageable pageable);
}
