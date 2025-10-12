package com.example.technology.domain.spi;

import com.example.technology.domain.model.Technology;
import reactor.core.publisher.Mono;

public interface TechnologyPersistencePort {
    Mono<Technology> save(Technology technology);

    Mono<Boolean> existByName(String name);

    Mono<Boolean> addCapacity(Long idCapacity, Long idTechnology);

    Mono<Boolean> existById(Long id);

    Mono<Boolean> existCapacityForTech(Long idCapacity, Long idTech);
}
