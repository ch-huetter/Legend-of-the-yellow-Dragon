package de.game.service.factory;

import de.game.model.entity.Attribute;
import de.game.model.entity.joinTable.PlayerCharacterAttribute;
import de.game.model.enums.AttributeEnum;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PlayerCharacterAttributeFactory {

    private final SettingService settingService;


    public Map<String, PlayerCharacterAttribute> createDefaultPlayerAttributes () {
        HashMap<String, PlayerCharacterAttribute> attributes = new HashMap<>();

        addPCA(attributes, AttributeEnum.AGILITY, SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_AGILITY);
        addPCA(attributes, AttributeEnum.DEXTERITY, SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_DEXTERITY);
        addPCA(attributes, AttributeEnum.STRENGTH, SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_STRENGTH);
        addPCA(attributes, AttributeEnum.INTELLIGENCE, SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_INTELLIGENCE);
        addPCA(attributes, AttributeEnum.VITALITY, SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_VITALITY);

        return attributes;
    }

    private void addPCA (HashMap<String, PlayerCharacterAttribute> attributes, AttributeEnum attributeEnum, SettingEnum setting) {
        Attribute                a              = Attribute.builder().key(attributeEnum.getKey()).build();
        Short                    attributeValue = Short.parseShort(settingService.getValue(setting));
        PlayerCharacterAttribute pCA            = PlayerCharacterAttribute.builder().attributeKey(a).value(attributeValue).build();
        attributes.put(attributeEnum.getKey(), pCA);

    }

}
