package de.game.service.factory.livingEntity;

import de.game.bean.dto.PlayerCharacterDto;
import de.game.model.entity.PlayerCharacter;
import de.game.util.helper.AttributeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerCharacterDtoFactory extends LivingEntityDtoFactory {

    private final AttributeHelper attributeHelper;

    public PlayerCharacterDto createPlayerCharacterDto (PlayerCharacter playerCharacter) {
        PlayerCharacterDto playerCharacterDto = new PlayerCharacterDto();
        playerCharacterDto.setAttributePoints(playerCharacter.getAttributePoints());
        playerCharacterDto.setExperienceForNextLevel(playerCharacter.getExperienceForNextLevel());
        playerCharacterDto.setPlayerClass(playerCharacter.getPlayerClass().name());
        playerCharacterDto.setAttributes(attributeHelper.getAttributeArray(playerCharacter));
        fillLivingEntityFields(playerCharacterDto, playerCharacter);
        return playerCharacterDto;
    }


}
