package com.example.technology.application;

import com.example.technology.domain.api.TechnologyServicePort;
import com.example.technology.domain.spi.TechnologyPersistencePort;
import com.example.technology.domain.usecase.TechnologyUseCase;
import com.example.technology.infrastructure.adapters.mysqladapter.MySQLAdapter;
import com.example.technology.infrastructure.adapters.mysqladapter.mapper.ITechnologyEntityMapper;
import com.example.technology.infrastructure.adapters.mysqladapter.repository.CapabilityTechnologyRepository;
import com.example.technology.infrastructure.adapters.mysqladapter.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCasesConfig {
    private final TechnologyRepository technologyRepository;
    private final CapabilityTechnologyRepository capacityTechnologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Bean
    public TechnologyPersistencePort technologyPersistencePort() {
        return new MySQLAdapter(technologyRepository, capacityTechnologyRepository, technologyEntityMapper);
    }

    @Bean
    public TechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase(technologyPersistencePort());
    }
}
