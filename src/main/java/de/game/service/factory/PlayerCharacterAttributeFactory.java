package de.game.service.factory;

import de.game.model.entity.joinTable.PlayerCharacterAttribute;
import de.game.service.SettingService;
import de.game.util.enums.SettingEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PlayerCharacterAttributeFactory {

    private final SettingService settingService;


    public Set<PlayerCharacterAttribute> createDefaultPlayerAttributes () {
        HashSet<PlayerCharacterAttribute> attributes = new HashSet<>();
        PlayerCharacterAttribute          attr       = new PlayerCharacterAttribute();
        //attr.setAttributeKey(GlobalConfigurationEnum.CHARACTER_START_ATTRIBUTE_VALUE_INTELLIGENCE.name());
        attr.setValue(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_INTELLIGENCE));
        attributes.add(attr);

        attr = new PlayerCharacterAttribute();
        //attr.setAttributeKey(GlobalConfigurationEnum.CHARACTER_START_ATTRIBUTE_VALUE_AGILITY.name());
        attr.setValue(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_AGILITY));
        attributes.add(attr);

        attr = new PlayerCharacterAttribute();
        //attr.setAttributeKey(GlobalConfigurationEnum.CHARACTER_START_ATTRIBUTE_VALUE_DEXTERITY.name());
        attr.setValue(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_DEXTERITY));
        attributes.add(attr);

        attr = new PlayerCharacterAttribute();
        //attr.setAttributeKey(GlobalConfigurationEnum.CHARACTER_START_ATTRIBUTE_VALUE_STRENGTH.name());
        attr.setValue(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_STRENGTH));
        attributes.add(attr);

        attr = new PlayerCharacterAttribute();
        //attr.setAttributeKey(GlobalConfigurationEnum.CHARACTER_START_ATTRIBUTE_VALUE_VITALITY.name());
        attr.setValue(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_VALUE_VITALITY));
        attributes.add(attr);

        return attributes;
    }

}
