package com.ms.email.producer;


import com.ms.email.dtos.CarDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CarProducer {

    private static final Logger logger = LoggerFactory.getLogger(CarProducer.class);
    private final String topic;
    private final KafkaTemplate<String, CarDto> kafkaTemplate;

    public CarProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, CarDto> kafkaTemplate1) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate1;
    }

    public void send(CarDto carDto){
        kafkaTemplate.send(topic, carDto).addCallback(
                success -> logger.info("Message send" + success.getProducerRecord().value()),
                failure -> logger.info("Message failure" + failure.getMessage())
        );
    }
}
