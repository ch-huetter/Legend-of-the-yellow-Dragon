package de.game.bean.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayerCharacterDto extends LivingEntityDto {


    private Integer attributePoints;
    private Integer experienceForNextLevel;

    private String playerClass;

    private AttributeDto[] attributes;
}
