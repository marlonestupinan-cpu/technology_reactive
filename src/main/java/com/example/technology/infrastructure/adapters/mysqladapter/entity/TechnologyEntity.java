package com.example.technology.infrastructure.adapters.mysqladapter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tecnologia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyEntity {
    @Id
    private Long id;
    @Column("nombre")
    private String name;
    @Column("descripcion")
    private String description;
}
