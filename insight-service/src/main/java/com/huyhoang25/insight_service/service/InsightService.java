package com.huyhoang25.insight_service.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import com.huyhoang25.insight_service.client.UsageClient;
import com.huyhoang25.insight_service.dto.DeviceDto;
import com.huyhoang25.insight_service.dto.InsightDto;
import com.huyhoang25.insight_service.dto.UsageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class InsightService {

    private final UsageClient usageClient;
    private final OpenAiChatModel openAiChatModel;

    public InsightDto getSavingsTips(Long userId) {

        // Fetch data from Usage Service
        final UsageDto usageData = usageClient.getXDaysUsageForUser(userId, 3);

        double totalUsage = usageData.devices().stream()
                .mapToDouble(DeviceDto::energyConsumed)
                .sum();

        log.info("Calling Ollama for userId {} with total usage {}", userId, totalUsage);

        String prompt = new StringBuilder()
                .append("This is my total consumption over the past 3 days.")
                .append("How can I reduce my energy consumption? How does it compare to average households?")
                .append("Total energy used: \n")
                .append(totalUsage)
                .toString();

        // ChatResponse response = openAiChatModel.call(
        //         Prompt.builder()
        //                 .content(prompt)
        //                 .build());

        ChatResponse response = openAiChatModel.call(
                        new Prompt(prompt));

        return InsightDto.builder()
                .userId(userId)
                .tips(response.getResult().getOutput().getText())
                .energyUsage(totalUsage)
                .build();
    }

    public InsightDto getOverview(Long userId) {
        // Fetch data from Usage Service
        final UsageDto usageData = usageClient.getXDaysUsageForUser(userId, 3);

        double totalUsage = usageData.devices().stream()
                .mapToDouble(DeviceDto::energyConsumed)
                .sum();

        log.info("Calling Ollama for userId {} with total usage {}",
                userId, totalUsage);

        String prompt = new StringBuilder()
                .append("Analyse the following energy usage data and provide a " +
                        "concise overview with actionable insights.")
                .append("This data is the aggregate data for the past 3 days.")
                .append("Usage Data: \n")
                .append(usageData.devices())
                .toString();

        // ChatResponse response = openAiChatModel.call(
        //         Prompt.builder()
        //                 .content(prompt)
        //                 .build());

        ChatResponse response = openAiChatModel.call(
                        new Prompt(prompt));

        return InsightDto.builder()
                .userId(userId)
                .tips(response.getResult().getOutput().getText())
                .energyUsage(totalUsage)
                .build();
    }

}
