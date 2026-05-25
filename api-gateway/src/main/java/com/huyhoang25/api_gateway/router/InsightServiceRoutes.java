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
public class InsightServiceRoutes {

    @Bean
    public RouterFunction<ServerResponse> insightRoute() {
        return GatewayRouterFunctions.route("insight-service")
                .route(RequestPredicates.path("/api/v1/insight/**"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8085"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> insightFallbackRoute() {
        return GatewayRouterFunctions.route("fallbackRoute")
                .route(RequestPredicates.path("/fallbackRoute"),
                        request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Insight serivce is down"))
                .build();
    }
}
