package de.game.controller.dto.character;

import de.game.bean.dto.AttributeDto;
import lombok.Data;

@Data
public abstract class AbstractCharacterDto {
    private String name;
    private AttributeDto[] attributes;
    private Integer attributePoints;
}
