package de.game.controller.dto.character;

import de.game.model.entity.joinTable.PlayerCharacterAttribute;
import lombok.Data;

import java.util.List;

@Data
public abstract class AbstractCharacterDto {
    private String name;
    private List<PlayerCharacterAttribute> attributes;
    private Integer attributePoints;
}
