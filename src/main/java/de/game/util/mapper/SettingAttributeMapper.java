package de.game.util.mapper;

import de.game.model.enums.AttributeEnum;
import de.game.model.enums.SettingEnum;

public class SettingAttributeMapper {

    public SettingEnum getStartSettingForAttributeKey (AttributeEnum attribute) {
        return switch (attribute) {
            case VITALITY -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_VITALITY;
            case STRENGTH -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_STRENGTH;
            case INTELLIGENCE -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_INTELLIGENCE;
            case DEXTERITY -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_DEXTERITY;
            case AGILITY -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_AGILITY;
            case ENDURANCE -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_ENDURANCE;
        };
    }

    public SettingEnum getStartSettingForAttributeKey (String attributeKey) {
        return switch (attributeKey) {
            case "vitality" -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_VITALITY;
            case "strength" -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_STRENGTH;
            case "intelligence" -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_INTELLIGENCE;
            case "dexterity" -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_DEXTERITY;
            case "agility" -> SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_AGILITY;
            default -> throw new IllegalStateException("Unexpected value: " + attributeKey);
        };
    }
}
