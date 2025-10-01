package de.game.service.mapper;

import de.game.controller.dto.CharacterCreationDto;
import de.game.model.enums.SettingEnum;
import de.game.service.SettingService;
import de.game.util.enums.SharedUiEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterCreationMapper {

    private final SettingService settingService;

    public void fill (CharacterCreationDto dto) {

        dto.setAbilityTreeItemPrefix(SharedUiEnum.CHARACTER_CREATION_ABILITY_TREE_PREFIX.getValue());
        dto.setAttributeDisplayPrefix(SharedUiEnum.CHARACTER_CREATION_ATTRIBUTE_DISPLAY_PREFIX.getValue());
        dto.setAttributeArrowRightPrefix(SharedUiEnum.CHARACTER_CREATION_ATTRIBUTE_ARROW_RIGHT_PREFIX.getValue());
        dto.setAttributeArrowLeftPrefix(SharedUiEnum.CHARACTER_CREATION_ATTRIBUTE_ARROW_LEFT_PREFIX.getValue());
        dto.setAttributeInputId(SharedUiEnum.CHARACTER_CREATION_ABILITY_TREE_INPUT_ID.getValue());
        dto.setAttributeMin(Integer.valueOf(settingService.getValue(SettingEnum.CHARACTER_ATTRIBUTE_MIN)));

    }

}
