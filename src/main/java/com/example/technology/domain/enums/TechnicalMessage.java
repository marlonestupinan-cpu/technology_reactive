package com.example.technology.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.helpers.MessageFormatter;

import java.util.List;
@Getter
@RequiredArgsConstructor
public enum TechnicalMessage {
    TECHNOLOGY_ADDED("201", "Tecnología añadida correctamente"),
    INTERNAL_ERROR("500", "Algo salió mal, intentalo de nuevo"),
    INVALID_REQUEST("400", "Petición erronea, verifica los datos"),
    INVALID_PARAMETERS(INVALID_REQUEST.getCode(), "Parametros erroneos, por favor verifica los datos"),
    INVALID_TECH_NAME("400", "El nombre supera los caracteres máximos"),
    INVALID_TECH_DESCRIPTION("400", "La descripción supera los caracteres máximos"),
    DUPLICATE_TECH_NAME("400", "El nombre ya existe."),
    TECH_NOT_FOUND("404", "La tecnología con id {} no existe"),
    CAPACITY_ALREADY_EXIST("400", "Ya existe la capacidad {} en la tecnología {}");

    private final String code;
    private final String message;

    public String getMessage(String[] params) {
        return MessageFormatter.arrayFormat(message, params).getMessage();
    }
}
