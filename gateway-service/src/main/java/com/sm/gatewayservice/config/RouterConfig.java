package com.sm.gatewayservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Value("${routes.uri.social-media}")
    private String socialMedia;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/sm/**")
                        .filters(f -> f.rewritePath("/sm/(?<path>.*)", "/$\\{path}"))
                        .uri(socialMedia))
                .build();

    }
}
