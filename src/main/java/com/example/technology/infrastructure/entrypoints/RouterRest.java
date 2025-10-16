package com.example.technology.infrastructure.entrypoints;

import com.example.technology.domain.model.Technology;
import com.example.technology.infrastructure.entrypoints.dto.AddCapacityDto;
import com.example.technology.infrastructure.entrypoints.dto.TechnologyDto;
import com.example.technology.infrastructure.entrypoints.handler.TechnologyHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration

public class RouterRest {
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/tech",
                    method = RequestMethod.POST,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "addTechnology",
                    operation = @Operation(
                            operationId = "addTechnology",
                            summary = "Add a new technology",
                            tags = {"Technology"},
                            requestBody = @RequestBody(
                                    description = "New technology data",
                                    required = true,
                                    content = @Content(schema = @Schema(implementation = TechnologyDto.class))
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Technology added successfully"),
                                    @ApiResponse(responseCode = "400", description = "Invalid input data")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/tech/capability",
                    method = RequestMethod.POST,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "addCapacitiesToTechnology",
                    operation = @Operation(
                            operationId = "addCapacitiesToTechnology",
                            summary = "Add capacities to a technology",
                            tags = {"Technology"},
                            requestBody = @RequestBody(
                                    description = "Capacity and technology IDs",
                                    required = true,
                                    content = @Content(schema = @Schema(implementation = AddCapacityDto.class))
                            ),
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Relationship created successfully"),
                                    @ApiResponse(responseCode = "400", description = "Invalid IDs provided")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/tech/capability/{id}",
                    method = RequestMethod.GET,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "getAllTechnologiesByCapacity",
                    operation = @Operation(
                            operationId = "getAllTechnologiesByCapacity",
                            summary = "Get all technologies for a given capacity ID",
                            tags = {"Technology"},
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Capacity ID", required = true)
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Successful operation",
                                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                                    schema = @Schema(implementation = Technology.class))),
                                    @ApiResponse(responseCode = "404", description = "Capacity not found")
                            }
                    )
            ),
            @RouterOperation(
                    path = "/tech/capability/{id}",
                    method = RequestMethod.DELETE,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "deleteCapability",
                    operation = @Operation(
                            operationId = "deleteCapability",
                            summary = "Delete a capacity relationship",
                            tags = {"Technology"},
                            parameters = {
                                    @Parameter(in = ParameterIn.PATH, name = "id", description = "Capability ID to delete", required = true)
                            },
                            responses = {
                                    @ApiResponse(responseCode = "200", description = "Relationship deleted successfully"),
                                    @ApiResponse(responseCode = "404", description = "Capability not found")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(TechnologyHandler technologyHandler) {
        return route(POST("/tech"), technologyHandler::addTechnology)
                .andRoute(POST("/tech/capability"), technologyHandler::addCapacitiesToTechnology)
                .andRoute(GET("/tech/capability/{id}"), technologyHandler::getAllTechnologiesByCapacity )
                .andRoute(DELETE("/tech/capability/{id}"), technologyHandler::deleteCapability);
    }
}
