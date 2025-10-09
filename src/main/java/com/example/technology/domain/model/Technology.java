package com.example.technology.domain.model;

import com.example.technology.domain.enums.TechnicalMessage;
import com.example.technology.domain.exceptions.BusinessException;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Technology {
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_DESCRIPTION_LENGTH = 90;

    private Long id;
    private String name;
    private String description;

    public void setName(String name) {
        if (name.length() > MAX_NAME_LENGTH)
            throw new BusinessException(TechnicalMessage.INVALID_TECH_NAME);
        this.name = name;
    }

    public void setDescription(String description) {
        if (name.length() > MAX_DESCRIPTION_LENGTH)
            throw new BusinessException(TechnicalMessage.INVALID_TECH_DESCRIPTION);
        this.description = description;
    }
}
