package com.example.technology.domain.api;

import com.example.technology.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyServicePort {
    Mono<Technology> addTechnology(Technology technology);
    Mono<Boolean> addCapacityToTechnology(Long idCapacity, List<Long> technologies);

    Flux<Technology> getAllTechnologiesByCapacity(Long id);

    Mono<Boolean> deleteCapability(Long idCapability);
}
