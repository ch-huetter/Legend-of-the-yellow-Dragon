package de.game.configuration.validator.playerCharacter;

import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.joinTable.PlayerCharacterAttribute;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.service.getter.PlayerCharacterGetter;
import de.game.util.helper.PlayerCharacterAttributeHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AttributeValidator {

    private final PlayerCharacterAttributeHelper attributeHelper;
    private final PlayerCharacterGetter playerCharacterGetter;
    private final SettingService settingsService;

    public boolean validateSpentAttributePoints (PlayerCharacter playerCharacter) {
        return (attributeHelper.calculateSpendAttributePoints(playerCharacter) <= attributeHelper.getAttributePointsForLevel(Integer.valueOf(playerCharacter.getLevel())));
    }

    public boolean validateMinAttributePointLimit (PlayerCharacter playerCharacter) {
        PlayerCharacter playerCharacterFromDb = playerCharacterGetter.getPlayerCharacterById(playerCharacter.getName());

        if (playerCharacterFromDb == null) {
            final int attributeMin = Integer.parseInt(settingsService.getValue(SettingEnum.CHARACTER_ATTRIBUTE_MIN));
            for (PlayerCharacterAttribute attribute : playerCharacter.getAttributes()) {
                if (attribute.getValue() < attributeMin)
                    return false;
            }
        } else {
            for (PlayerCharacterAttribute attribute : playerCharacter.getAttributes()) {
                PlayerCharacterAttribute attributeFromDbCharacter =
                        playerCharacterFromDb.getAttributes().stream()
                                .filter(attrFromDbCharacter -> attrFromDbCharacter.getAttributeKey().getKey().equals(attribute.getAttributeKey().getKey())).findFirst()
                                .orElseThrow();
                if (attribute.getValue() < attributeFromDbCharacter.getValue())
                    return false;
            }
        }
        return true;
    }

}
