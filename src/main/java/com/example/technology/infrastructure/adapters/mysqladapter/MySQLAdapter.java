package com.example.technology.infrastructure.adapters.mysqladapter;

import com.example.technology.domain.model.Technology;
import com.example.technology.domain.spi.TechnologyPersistencePort;
import com.example.technology.infrastructure.adapters.mysqladapter.entity.TechnologyEntity;
import com.example.technology.infrastructure.adapters.mysqladapter.mapper.ITechnologyEntityMapper;
import com.example.technology.infrastructure.adapters.mysqladapter.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MySQLAdapter implements TechnologyPersistencePort {
    private final TechnologyRepository technologyRepository;
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
}
