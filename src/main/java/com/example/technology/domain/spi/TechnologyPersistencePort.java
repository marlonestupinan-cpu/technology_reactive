package com.example.technology.domain.spi;

import com.example.technology.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TechnologyPersistencePort {
    Mono<Technology> save(Technology technology);

    Mono<Boolean> existByName(String name);

    Mono<Boolean> addCapability(Long idCapacity, Long idTechnology);

    Mono<Boolean> existById(Long id);

    Mono<Boolean> existCapabilityForTech(Long idCapability, Long idTech);

    Flux<Technology> getAllByCapability(Long idCapability);

    Flux<Technology> deleteAllCapabilityTechnology(Long idCapability);

    Mono<Boolean> hasCapabilityAssociated(Technology technology);

    Mono<Void> delete(Technology technology);
}
