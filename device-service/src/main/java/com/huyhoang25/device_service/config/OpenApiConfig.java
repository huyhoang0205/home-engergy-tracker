package com.huyhoang25.device_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Device Service API")
                        .description("Device service API for Home Energy Tracker Project")
                        .contact(getContact())
                        .license(getLicense())
                        .version("1.0.0"));
    }

    private static License getLicense() {
        License license = new License();
        license.setName("Creative Commons Attribution-NonCommercial 4.0 International License");
        license.setUrl("https://creativecommons.org/licenses/by-nc/4.0/");
        return license;
    }

    private static Contact getContact() {
        Contact contact = new Contact();
        contact.setEmail("ndhhoang.02052002@gmail.com");
        return contact;
    }
}
