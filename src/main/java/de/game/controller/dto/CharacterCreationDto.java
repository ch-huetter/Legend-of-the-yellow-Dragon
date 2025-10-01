package de.game.controller.dto;

import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.PlayerClass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class CharacterCreationDto {

    private PlayerCharacter playerCharacter;
    private List<PlayerClass> playerClasses;
    //TODO CharacterCreation korrigieren. Sie verweist noch auf AbilityTree

    //Create Charakter Attributes
    private Integer attributeMin;
    private Integer activeAbilitySetId;

    //Initialization of Create Charakter Attributes
    private String attributeInputId;
    private String attributeArrowLeftPrefix;
    private String attributeArrowRightPrefix;
    private String attributeDisplayPrefix;
    private String abilityTreeItemPrefix;
    private String pointsDisplayMessageWPL;
}
