package com.example.technology.infrastructure.adapters.mysqladapter.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("capacidad_tecnologias")
public class CapacityTechnologyEntity {
    @Id
    private Long id;
    @Column("id_capacidad")
    private Long idCapacity;
    @Column("id_tecnologia")
    private Long idTechnology;
}
