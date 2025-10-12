package com.example.technology.infrastructure.adapters.mysqladapter.repository;

import com.example.technology.domain.model.Technology;
import com.example.technology.infrastructure.adapters.mysqladapter.entity.CapacityTechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CapacityTechnologyRepository extends ReactiveCrudRepository<CapacityTechnologyEntity, Long> {
    Mono<Boolean> existsByIdCapacityAndIdTechnology(Long idCapacity, Long idTech);
}
