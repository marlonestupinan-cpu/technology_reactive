package com.example.technology.infrastructure.adapters.mysqladapter.repository;

import com.example.technology.infrastructure.adapters.mysqladapter.entity.TechnologyEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TechnologyRepository extends ReactiveCrudRepository<TechnologyEntity, Long> {
    Mono<Boolean> existsByName(String name);
}
