package com.example.technology.infrastructure.adapters.mysqladapter;

import com.example.technology.domain.model.Technology;
import com.example.technology.domain.spi.TechnologyPersistencePort;
import com.example.technology.infrastructure.adapters.mysqladapter.entity.CapacityTechnologyEntity;
import com.example.technology.infrastructure.adapters.mysqladapter.entity.TechnologyEntity;
import com.example.technology.infrastructure.adapters.mysqladapter.mapper.ITechnologyEntityMapper;
import com.example.technology.infrastructure.adapters.mysqladapter.repository.CapacityTechnologyRepository;
import com.example.technology.infrastructure.adapters.mysqladapter.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MySQLAdapter implements TechnologyPersistencePort {
    private final TechnologyRepository technologyRepository;
    private final CapacityTechnologyRepository capacityTechnologyRepository;
    private final ITechnologyEntityMapper technologyMapper;

    @Override
    public Mono<Technology> save(Technology technology) {
        TechnologyEntity entity = technologyMapper.toTechnologyEntity(technology);
        return technologyRepository
                .save(entity)
                .map(technologyMapper::toTechnology);
    }

    @Override
    public Mono<Boolean> existByName(String name) {
        return technologyRepository.existsByName(name);
    }

    @Override
    public Mono<Boolean> addCapacity(Long idCapacity, Long idTechnology) {
        return capacityTechnologyRepository
                .save(
                        CapacityTechnologyEntity.builder()
                                .idCapacity(idCapacity)
                                .idTechnology(idTechnology)
                                .build()
                ).hasElement();
    }

    @Override
    public Mono<Boolean> existById(Long id) {
        return technologyRepository.existsById(id);
    }

    @Override
    public Mono<Boolean> existCapacityForTech(Long idCapacity, Long idTech) {
        return capacityTechnologyRepository.existsByIdCapacityAndIdTechnology(idCapacity, idTech);
    }
}
