package de.game.controller.dto.character;

import de.game.configuration.constraintValidator.annotation.ValidCharacterCreationDto;
import de.game.model.entity.PlayerClass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ValidCharacterCreationDto
public class CharacterCreationDto extends AbstractCharacterDto {

    private List<PlayerClass> playerClasses;

    //Create Charakter Attributes
    private Integer attributeMin;
    private Integer activePlayerClassId;

    //Initialization of Create Charakter Attributes
    private String attributeArrowLeftPrefix;
    private String attributeArrowRightPrefix;
    private String attributeDisplayPrefix;
    private String playerClassInputId;
    private String playerClassItemPrefix;
    private String pointsDisplayMessageWPL;
}
