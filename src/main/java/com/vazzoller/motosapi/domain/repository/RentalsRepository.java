package com.vazzoller.motosapi.domain.repository;

import com.vazzoller.motosapi.domain.model.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals, UUID> {
}
