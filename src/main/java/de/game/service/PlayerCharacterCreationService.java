package de.game.service;

import de.game.apiController.sites.characterCreation.CharacterCreationApiController;
import de.game.model.entity.PlayerCharacter;
import de.game.model.enums.AttributeEnum;
import de.game.model.enums.PlayerClassEnum;
import de.game.util.attribute.valueCalculator.PlayerCharacterValueCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlayerCharacterCreationService {

    private final PlayerCharacterValueCalculator playerCharacterValueCalculator;

    public void createCharacter (CharacterCreationApiController.CharacterCreationRequest characterCreationRequest) {
        PlayerCharacter playerCharacter = new PlayerCharacter();
        playerCharacter.setName(characterCreationRequest.name());
        characterCreationRequest.attributes().forEach(
                attributeSelectionEntry -> {
                    Arrays.stream(AttributeEnum.values()).filter(attributeEnum -> attributeEnum.getKey().equals(attributeSelectionEntry.getKey())).findFirst()
                            .orElseThrow(NullPointerException::new).getSetter().accept(playerCharacter,
                                                                                       attributeSelectionEntry.getValue());
                });

        playerCharacter.setPlayerClass(
                Arrays.stream(PlayerClassEnum.values()).filter(playerClassEnum -> Objects.equals(playerClassEnum.getId(), characterCreationRequest.activePlayerClass())).findFirst()
                        .orElseThrow(NullPointerException::new));

        playerCharacterValueCalculator.calculateEntityValues(playerCharacter);

    }
}
