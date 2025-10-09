package com.example.technology.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechnicalMessage {
    TECHNOLOGY_ADDED("201", "Tecnología añadida correctamente", ""),
    INTERNAL_ERROR("500", "Algo salió mal, intentalo de nuevo", ""),
    INVALID_REQUEST("400", "Petición erronea, verifica los datos", ""),
    INVALID_PARAMETERS(INVALID_REQUEST.getCode(), "Parametros erroneos, por favor verifica los datos", ""),
    INVALID_TECH_NAME("400", "El nombre supera los caracteres máximos", ""),
    INVALID_TECH_DESCRIPTION("400", "La descripción supera los caracteres máximos", ""),
    DUPLICATE_TECH_NAME("400", "El nombre ya existe.", ""),
    ;
    private final String code;
    private final String message;
    private final String param;
}
