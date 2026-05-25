package com.huyhoang25.api_gateway.router;

import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class IngestionServiceRoutes {

    @Bean
    public RouterFunction<ServerResponse> ingestionRoute() {
        return GatewayRouterFunctions.route("ingestion-service")
                .route(RequestPredicates.path("/api/v1/ingestion/**"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8082"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> ingestionFallbackRoute() {
        return GatewayRouterFunctions.route("fallbackRoute")
                .route(RequestPredicates.path("/fallbackRoute"),
                        request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Ingestion serivce is down"))
                .build();
    }
}
