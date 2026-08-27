package de.game.bean.frontendComponents;

import de.game.bean.dto.AttributeDto;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class AttributeSelectionEntryConverter {

    private final SettingService settingService;
    private Short minValueFromSettings;
    
    public List<AttributeSelectionEntry> convertLivingEntityAttributeList (AttributeDto[] playerCharacterAttributeList, Boolean useDefaultMin) {
        ArrayList<AttributeSelectionEntry> attributeSelectionEntries = new ArrayList<>();
        Arrays.stream(playerCharacterAttributeList).toList().forEach(playerCharacterAttribute -> {
            attributeSelectionEntries.add(convertPlayerClassAttribute(playerCharacterAttribute, useDefaultMin));
        });
        return attributeSelectionEntries;
    }

    public AttributeSelectionEntry convertPlayerClassAttribute (AttributeDto playerCharacterAttribute, Boolean useDefaultMin) {
        AttributeSelectionEntry selectionEntry = new AttributeSelectionEntry();
        selectionEntry.setKey(playerCharacterAttribute.getKey());
        selectionEntry.setValue((short) playerCharacterAttribute.getValue());
        selectionEntry.setMinValue(useDefaultMin ? getMinValue() : (short) playerCharacterAttribute.getValue());
        selectionEntry.setCanDecrease(selectionEntry.getValue() > selectionEntry.getMinValue());
        return selectionEntry;
    }

    private short getMinValue () {
        if (minValueFromSettings == null) {
            minValueFromSettings = Short.valueOf(settingService.getValue(SettingEnum.CHARACTER_ATTRIBUTE_MIN));
        }
        return minValueFromSettings;
    }

}
