package com.example.technology.infrastructure.adapters.mysqladapter.repository;

import com.example.technology.infrastructure.adapters.mysqladapter.entity.CapabilityTechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface CapabilityTechnologyRepository extends ReactiveCrudRepository<CapabilityTechnologyEntity, Long> {
    Mono<Boolean> existsByIdCapabilityAndIdTechnology(Long idCapability, Long idTech);

    Mono<Void> deleteAllByIdCapability(Long idCapability);

    Mono<Boolean> existsByIdTechnology(Long id);
}
