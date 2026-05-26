package de.game.util.helper;

import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.joinTable.PlayerCharacterAttribute;
import de.game.model.enums.AttributeEnum;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.util.mapper.SettingAttributeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerCharacterAttributeHelper {

    private final SettingService settingService;

    public PlayerCharacterAttribute findAttributeFromList (List<PlayerCharacterAttribute> attributeList, AttributeEnum searchAttribute) throws IllegalArgumentException {
        return attributeList.stream().filter(attribute -> attribute.getAttribute().getKey().equals(searchAttribute.getKey())).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Attribut " + searchAttribute.getKey() + " nicht gefunden"));

    }

    /**
     * Counts only spend Attribute Points and automatically Subtracts starting Values
     *
     * @return Integer
     */
    public Integer calculateSpendAttributePoints (PlayerCharacter character) {
        int total = 0;
        for (PlayerCharacterAttribute characterAttribute : character.getAttributes()) {
            total += calculateSpendAttributePointsForAttribute(characterAttribute);
        }
        return total;
    }

    public Integer calculateSpendAttributePointsForAttribute (PlayerCharacterAttribute characterAttribute) {
        return characterAttribute.getValue() -
               Integer.parseInt(settingService.getValue(SettingAttributeMapper.getStartSettingForAttribute(characterAttribute.getAttribute().getKey())));
    }

    public Integer getAttributePointsForLevel (Integer level) {
        return Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_POINTS)) + ((level - 1) * 2) + (level / 3) + ((level / 5) * 2);
    }
}

