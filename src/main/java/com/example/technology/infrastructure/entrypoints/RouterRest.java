package com.example.technology.infrastructure.entrypoints;

import com.example.technology.infrastructure.entrypoints.handler.TechnologyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(TechnologyHandler technologyHandler) {
        return route(POST("/tech"), technologyHandler::addTechnology)
                .andRoute(POST("/tech/capacity"), technologyHandler::addCapacitiesToTechnology);
    }
}
