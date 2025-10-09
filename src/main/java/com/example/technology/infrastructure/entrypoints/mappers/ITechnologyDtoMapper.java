package com.example.technology.infrastructure.entrypoints.mappers;

import com.example.technology.domain.model.Technology;
import com.example.technology.infrastructure.entrypoints.dto.TechnologyDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ITechnologyDtoMapper {
    Technology toTechnology(TechnologyDto technologyDto);
}
