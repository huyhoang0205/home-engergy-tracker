package com.huyhoang25.ingestion_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.huyhoang25.ingestion_service.dto.EnergyUsageDto;
import com.huyhoang25.ingestion_service.service.IngestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingestion")
public class IngestionController {

    private final IngestionService ingestionService;

    @PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public void ingestData(@RequestBody EnergyUsageDto usageDto) {
        ingestionService.ingestData(usageDto);
    }

}
