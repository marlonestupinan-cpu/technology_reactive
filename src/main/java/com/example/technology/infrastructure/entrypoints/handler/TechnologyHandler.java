package com.example.technology.infrastructure.entrypoints.handler;

import com.example.technology.domain.api.TechnologyServicePort;
import com.example.technology.domain.enums.TechnicalMessage;
import com.example.technology.domain.exceptions.BusinessException;
import com.example.technology.domain.exceptions.TechnicalException;
import com.example.technology.infrastructure.entrypoints.dto.AddCapacityDto;
import com.example.technology.infrastructure.entrypoints.dto.TechnologyDto;
import com.example.technology.infrastructure.entrypoints.mappers.ITechnologyDtoMapper;
import com.example.technology.infrastructure.entrypoints.util.APIResponse;
import com.example.technology.infrastructure.entrypoints.util.ErrorDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Log4j2
public class TechnologyHandler {
    private final TechnologyServicePort technologyServicePort;
    private final ITechnologyDtoMapper technologyDtoMapper;

    public Mono<ServerResponse> addTechnology(ServerRequest request) {
        return request
                .bodyToMono(TechnologyDto.class)
                .map(technologyDtoMapper::toTechnology)
                .flatMap(technologyServicePort::addTechnology)
                .flatMap(savedTechnology ->
                        ServerResponse
                                .ok()
                                .bodyValue(TechnicalMessage.TECHNOLOGY_ADDED.getMessage()))
                .transform(errorHandler());
    }

    public Mono<ServerResponse> addCapacitiesToTechnology(ServerRequest request) {
        return request
                .bodyToMono(AddCapacityDto.class)
                .flatMap(capacityDto -> technologyServicePort
                        .addCapacityToTechnology(capacityDto.getIdCapacity(), capacityDto.getTechnologies()))
                .flatMap(savedTechnology ->
                        ServerResponse.ok().build())
                .transform(errorHandler());
    }

    private Mono<ServerResponse> buildErrorResponse(HttpStatus httpStatus, TechnicalMessage error,
                                                    List<ErrorDTO> errors) {
        return Mono.defer(() -> {
            APIResponse apiErrorResponse = APIResponse
                    .builder()
                    .code(error.getCode())
                    .message(error.getMessage())
                    .date(Instant.now().toString())
                    .errors(errors)
                    .build();
            return ServerResponse.status(httpStatus)
                    .bodyValue(apiErrorResponse);
        });
    }

    private <T> Function<Mono<ServerResponse>, Mono<ServerResponse>> errorHandler() {
        return monoStream ->
                monoStream
                        .onErrorResume(BusinessException.class, ex -> buildErrorResponse(
                                HttpStatus.BAD_REQUEST,
                                TechnicalMessage.INVALID_PARAMETERS,
                                List.of(ErrorDTO.builder()
                                        .code(ex.getTechnicalMessage().getCode())
                                        .message(ex.getMessage())
                                        .build())))
                        .onErrorResume(TechnicalException.class, ex -> buildErrorResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                TechnicalMessage.INTERNAL_ERROR,
                                List.of(ErrorDTO.builder()
                                        .code(ex.getTechnicalMessage().getCode())
                                        .message(ex.getTechnicalMessage().getMessage())
                                        .build())))
                        .onErrorResume(ex -> {
                                    log.error("Unexpected error occurred for messageId: ", ex);
                                    return buildErrorResponse(
                                            HttpStatus.INTERNAL_SERVER_ERROR,
                                            TechnicalMessage.INTERNAL_ERROR,
                                            List.of(ErrorDTO.builder()
                                                    .code(TechnicalMessage.INTERNAL_ERROR.getCode())
                                                    .message(TechnicalMessage.INTERNAL_ERROR.getMessage())
                                                    .build()));
                                }
                        );
    }
}
