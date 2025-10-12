package com.example.technology.infrastructure.adapters.mysqladapter.repository;

import com.example.technology.domain.model.Technology;
import com.example.technology.infrastructure.adapters.mysqladapter.entity.TechnologyEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TechnologyRepository extends ReactiveCrudRepository<TechnologyEntity, Long> {
    Mono<Boolean> existsByName(String name);
    @Query("""
        SELECT tech.* FROM tecnologia tech
        INNER JOIN capacidad_tecnologias tc ON tech.id = tc.id_tecnologia
        WHERE tc.id_capacidad = :idCapacidad
    """)
    Flux<TechnologyEntity> findAllByCapabilityId(Long capabilityId);
}
