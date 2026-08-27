package de.game.apiController.sites.characterCreation;

import de.game.bean.frontendComponents.AttributeSelectionEntryConverter;
import de.game.model.enums.PlayerClassEnum;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.service.filler.dto.DtoFiller;
import de.game.util.helper.AttributeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CharacterCreationApiDtoFiller implements DtoFiller<CharacterCreationApiDto> {

    private final AttributeHelper attributeHelper;
    private final AttributeSelectionEntryConverter attributeSelectionEntryConverter;
    private final SettingService settingService;

    @Override
    public void fillDto (CharacterCreationApiDto dto) {
        dto.setAttributeSelectionEntries(
                attributeSelectionEntryConverter.convertLivingEntityAttributeList(attributeHelper.getAttributeArray(), true));
        dto.setAttributePoints(Short.valueOf(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_POINTS)));
        dto.setPlayerClasses(Arrays.stream(PlayerClassEnum.values()).toList());
        dto.setActivePlayerClass(Short.valueOf("0"));
    }

    @Override
    public Class<CharacterCreationApiDto> supports () {
        return CharacterCreationApiDto.class;
    }
}
