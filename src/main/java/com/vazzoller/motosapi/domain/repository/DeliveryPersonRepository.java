package com.vazzoller.motosapi.domain.repository;

import com.vazzoller.motosapi.domain.model.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, UUID> {
}
