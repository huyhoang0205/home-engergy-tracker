package com.huyhoang25.ingestion_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.huyhoang25.ingestion_service.dto.EnergyUsageDto;
import com.huyhoang25.kafka.event.EnergyUsageEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class IngestionService {

    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

    public void ingestData(EnergyUsageDto input) {
        // Convert Dto to event
        EnergyUsageEvent event = EnergyUsageEvent.builder()
                .deviceId(input.deviceId())
                .energyConsumed(input.energyConsumed())
                .timestamp(input.timestamp())
                .build();

        // Send event to kafka topic
        kafkaTemplate.send("energy-usage", event);
        log.info("Ingested Energy Usage Event {}", event);
    }

}
