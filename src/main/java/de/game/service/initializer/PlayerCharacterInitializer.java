package de.game.service.initializer;

import de.game.controller.dto.character.CharacterCreationDto;
import de.game.model.entity.PlayerCharacter;
import de.game.model.enums.PlayerClassEnum;
import de.game.service.factory.EnumToObjectFactory;
import de.game.service.factory.PlayerCharacterFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Fills in Values from the Character Creation Page.
 */
@RequiredArgsConstructor
@Service
public class PlayerCharacterInitializer {

    private final PlayerCharacterFactory pCF;
    private final EnumToObjectFactory enumToObjektFactory;

    public PlayerCharacter initAfterCreation (CharacterCreationDto characterDto) throws IllegalArgumentException {
        PlayerCharacter playerCharacter = pCF.getNewPlayerCharacterForCharacterCreation();
        playerCharacter.setName(characterDto.getName());

        playerCharacter.setAttributes(characterDto.getAttributes());
        playerCharacter.getAttributes().forEach(attr -> attr.setCharacterName(playerCharacter));

        PlayerClassEnum playerClassEnum = Arrays.stream(PlayerClassEnum.values()).filter(e ->
                                                                                                 e.getId().equals(characterDto.getActivePlayerClassId()))
                .findFirst().orElseThrow(IllegalArgumentException::new);
        playerCharacter.setPlayerClass(enumToObjektFactory.createPlayerClass(playerClassEnum));

        return playerCharacter;
    }

}
