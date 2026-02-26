package com.vazzoller.motosapi.messaging.consumer;

import com.vazzoller.motosapi.domain.model.MotorcycleNotification;
import com.vazzoller.motosapi.domain.repository.MotorcycleNotificationRepository;
import com.vazzoller.motosapi.services.dtos.motorcycle.MotorcycleResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MotorcycleYear2024Consumer extends BaseConsumer<MotorcycleResponse> {

    @Autowired
    private MotorcycleNotificationRepository motorcycleNotificationRepository;

    @Override
    public String getQueueName() {
        return "motorcycle.year.2024.created";
    }

    @RabbitListener(queues = "motorcycle.year.2024.created")
    @Override
    public void receiveMessage(MotorcycleResponse response) {
        super.receiveMessage(response);
    }

    @Override
    public void consume(MotorcycleResponse response) {
        MotorcycleNotification motorcycleNotification = new MotorcycleNotification(
                response.getId(),
                response.getYear(),
                response.getModel(),
                response.getPlate()
        );

        motorcycleNotificationRepository.save(motorcycleNotification);
    }
}
