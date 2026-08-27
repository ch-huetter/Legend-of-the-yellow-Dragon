package de.game.configuration.validator.playerCharacter;

import de.game.model.entity.PlayerCharacter;
import de.game.model.enums.AttributeEnum;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.service.getter.PlayerCharacterGetter;
import de.game.util.helper.AttributeHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AttributeValidator {

    private final AttributeHelper attributeHelper;
    private final PlayerCharacterGetter playerCharacterGetter;
    private final SettingService settingsService;

    public boolean validateSpentAttributePoints (PlayerCharacter playerCharacter) {
        return (attributeHelper.getSpendAttributePoints(playerCharacter) <= attributeHelper.getAttributePointsForLevel(Integer.valueOf(playerCharacter.getLevel())));
    }

    /**
     * Checks if a LivingEntity has the minimum amount of Points in every Attribute. References Attributes for AttributeEnum.
     * Fresh Characters cant have a value that is smaller than the CHARACTER_ATTRIBUTE_MIN Settings value.
     * For existing Characters Attributes can't be lower than their old Values
     *
     * @param playerCharacter to check
     * @return true if all Attributes have at least the minimum Value or false if some are below
     */
    public boolean validateMinAttributePointLimit (PlayerCharacter playerCharacter) {
        PlayerCharacter playerCharacterFromDb = playerCharacterGetter.getPlayerCharacterById(playerCharacter.getId());

        if (playerCharacterFromDb == null) {
            final int attributeMin = Integer.parseInt(settingsService.getValue(SettingEnum.CHARACTER_ATTRIBUTE_MIN));
            for (AttributeEnum attributeEnum : AttributeEnum.values()) {
                if (attributeEnum.getGetter().apply(playerCharacter) < attributeMin)
                    return false;
            }
        } else {
            for (AttributeEnum attributeEnum : AttributeEnum.values()) {
                if (attributeEnum.getGetter().apply(playerCharacter) < attributeEnum.getGetter().apply(playerCharacterFromDb))
                    return false;
            }
        }
        return true;
    }

}
