package com.example.technology.infrastructure.adapters.mysqladapter;

import com.example.technology.domain.model.Technology;
import com.example.technology.domain.spi.TechnologyPersistencePort;
import com.example.technology.infrastructure.adapters.mysqladapter.entity.CapabilityTechnologyEntity;
import com.example.technology.infrastructure.adapters.mysqladapter.entity.TechnologyEntity;
import com.example.technology.infrastructure.adapters.mysqladapter.mapper.ITechnologyEntityMapper;
import com.example.technology.infrastructure.adapters.mysqladapter.repository.CapabilityTechnologyRepository;
import com.example.technology.infrastructure.adapters.mysqladapter.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MySQLAdapter implements TechnologyPersistencePort {
    private final TechnologyRepository technologyRepository;
    private final CapabilityTechnologyRepository capabilityTechnologyRepository;
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
    public Mono<Boolean> addCapability(Long idCapacity, Long idTechnology) {
        return capabilityTechnologyRepository
                .save(
                        CapabilityTechnologyEntity.builder()
                                .idCapability(idCapacity)
                                .idTechnology(idTechnology)
                                .build()
                ).hasElement();
    }

    @Override
    public Mono<Boolean> existById(Long id) {
        return technologyRepository.existsById(id);
    }

    @Override
    public Mono<Boolean> existCapabilityForTech(Long idCapability, Long idTech) {
        return capabilityTechnologyRepository.existsByIdCapabilityAndIdTechnology(idCapability, idTech);
    }

    @Override
    public Flux<Technology> getAllByCapability(Long idCapability) {
        return technologyRepository
                .findAllByCapabilityId(idCapability)
                .map(technologyMapper::toTechnology);
    }

    @Override
    public Flux<Technology> deleteAllCapabilityTechnology(Long idCapability) {
        return getAllByCapability(idCapability)
                .collectList()
                .flatMapMany(technologies ->
                        capabilityTechnologyRepository
                                .deleteAllByIdCapability(idCapability)
                                .thenMany(Flux.fromIterable(technologies)));
    }

    @Override
    public Mono<Boolean> hasCapabilityAssociated(Technology technology) {
        return capabilityTechnologyRepository.existsByIdTechnology(technology.getId());
    }

    @Override
    public Mono<Void> delete(Technology technology) {
        return technologyRepository.deleteById(technology.getId());
    }
}
