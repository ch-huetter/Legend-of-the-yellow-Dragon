package de.game.service.filler.dto.characterDto;

import de.game.controller.dto.character.CharacterCreationDto;
import de.game.model.enums.PlayerClassEnum;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.service.filler.dto.DtoFiller;
import de.game.util.enums.SharedUiEnum;
import de.game.util.helper.AttributeHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CreationDtoFiller implements DtoFiller<CharacterCreationDto> {

    private final SettingService settingService;
    private final MessageSource messageSource;
    private final AttributeHelper attributeHelper;

    @Override
    public void fillDto (CharacterCreationDto dto) {
        if (dto.getAttributes() == null)
            dto.setAttributes(attributeHelper.getAttributeArray());
        if (dto.getAttributePoints() == null)
            dto.setAttributePoints(Integer.parseInt(settingService.getValue(SettingEnum.CHARACTER_START_ATTRIBUTE_POINTS)));
        if (dto.getActivePlayerClassId() == null)
            dto.setActivePlayerClassId(0);
        if (dto.getPlayerClasses() == null) {
            dto.setPlayerClasses(Arrays.stream(PlayerClassEnum.values()).toList());
        }

        dto.setPlayerClassItemPrefix(SharedUiEnum.CHARACTER_CREATION_PLAYER_ClASS_ITEM_PREFIX.getValue());
        dto.setAttributeDisplayPrefix(SharedUiEnum.CHARACTER_CREATION_ATTRIBUTE_DISPLAY_PREFIX.getValue());
        dto.setAttributeArrowRightPrefix(SharedUiEnum.CHARACTER_CREATION_ATTRIBUTE_ARROW_RIGHT_PREFIX.getValue());
        dto.setAttributeArrowLeftPrefix(SharedUiEnum.CHARACTER_CREATION_ATTRIBUTE_ARROW_LEFT_PREFIX.getValue());
        dto.setPlayerClassInputId(SharedUiEnum.CHARACTER_CREATION_PlAYER_CLASS_INPUT_ID.getValue());
        dto.setAttributeMin(Integer.valueOf(settingService.getValue(SettingEnum.CHARACTER_ATTRIBUTE_MIN)));

        dto.setPointsDisplayMessageWPL(messageSource.getMessage("characterCreation.pointDisplay.withPlaceholder", null, LocaleContextHolder.getLocale()));
    }

    /**
     * @return CharacterCreationDto.class
     */
    @Override
    public Class<CharacterCreationDto> supports () {
        return CharacterCreationDto.class;
    }
}
