package com.example.technology.infrastructure.adapters.mysqladapter.mapper;

import com.example.technology.domain.model.Technology;
import com.example.technology.infrastructure.adapters.mysqladapter.entity.TechnologyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ITechnologyEntityMapper {
    Technology toTechnology(TechnologyEntity technologyEntity);
    TechnologyEntity toTechnologyEntity(Technology technology);
}
