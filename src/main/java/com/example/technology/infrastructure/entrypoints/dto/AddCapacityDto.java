package com.example.technology.infrastructure.entrypoints.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddCapacityDto {
    private Long IdCapacity;
    private List<Long> technologies;
}
