package com.vazzoller.motosapi.domain.repository;

import com.vazzoller.motosapi.domain.model.MotorcycleNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface MotorcycleNotificationRepository extends JpaRepository<MotorcycleNotification, UUID> {

}
