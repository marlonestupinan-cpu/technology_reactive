package com.example.technology.domain.usecase;

import com.example.technology.domain.api.TechnologyServicePort;
import com.example.technology.domain.enums.TechnicalMessage;
import com.example.technology.domain.exceptions.BusinessException;
import com.example.technology.domain.model.Technology;
import com.example.technology.domain.spi.TechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TechnologyUseCase implements TechnologyServicePort {
    private final TechnologyPersistencePort technologyPersistencePort;

    @Override
    public Mono<Technology> addTechnology(Technology technology) {
        return technologyPersistencePort
                .existByName(technology.getName())
                .filter(exist -> !exist)
                .flatMap(exist -> technologyPersistencePort.save(technology))
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.DUPLICATE_TECH_NAME)));
    }
}
