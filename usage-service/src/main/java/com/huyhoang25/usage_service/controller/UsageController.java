package com.huyhoang25.usage_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huyhoang25.usage_service.dto.UsageDto;
import com.huyhoang25.usage_service.service.UsageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usage")
@RequiredArgsConstructor
public class UsageController {

    private final UsageService usageService;

    @GetMapping("/{userId}")
    public ResponseEntity<UsageDto> getUserDeviceUsage(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "3") int days) {
        final UsageDto usage = usageService.getXDaysUsageForUser(userId, days);
        return ResponseEntity.ok(usage);
    }
}
