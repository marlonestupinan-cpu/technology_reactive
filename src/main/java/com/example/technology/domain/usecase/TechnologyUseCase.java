package com.example.technology.domain.usecase;

import com.example.technology.domain.api.TechnologyServicePort;
import com.example.technology.domain.enums.TechnicalMessage;
import com.example.technology.domain.exceptions.BusinessException;
import com.example.technology.domain.model.Technology;
import com.example.technology.domain.spi.TechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Transactional
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

    @Override
    public Mono<Boolean> addCapacityToTechnology(Long idCapacity, List<Long> technologies) {
        return Flux
                .fromIterable(technologies)
                .concatMap(idTech ->
                        technologyPersistencePort
                                .existById(idTech)
                                .filter(exist -> exist)
                                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.TECH_NOT_FOUND, idTech.toString())))
                                .flatMap(exist -> technologyPersistencePort
                                        .existCapacityForTech(idCapacity, idTech)
                                        .filter(existCapacity -> !existCapacity)
                                        .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.CAPACITY_ALREADY_EXIST, idTech.toString(), idCapacity.toString())))
                                        .flatMap(existCapacity -> technologyPersistencePort.addCapacity(idCapacity, idTech))
                                )
                )
                .then(Mono.just(true));
    }

    @Override
    public Flux<Technology> getAllTechnologiesByCapacity(Long capacityId) {
        return technologyPersistencePort.getAllByCapacity(capacityId);
    }
}
