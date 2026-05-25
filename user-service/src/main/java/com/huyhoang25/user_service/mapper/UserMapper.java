package com.huyhoang25.user_service.mapper;

import org.springframework.stereotype.Component;

import com.huyhoang25.user_service.dto.UserDto;
import com.huyhoang25.user_service.entity.User;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .address(user.getAddress())
                .alerting(user.isAlerting())
                .energyAlertingThreshold(user.getEnergyAlertingThreshold())
                .build();
    }
}
