package de.game.service;

import de.game.controller.dto.CharacterCreationDto;
import de.game.service.factory.PlayerCharacterFactory;
import de.game.service.mapper.CharacterCreationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterCreationDtoFiller {

    private final PlayerCharacterFactory playerCharacterFactory;
    private final CharacterCreationMapper characterCreationMapper;
    private final MessageSource messageSource;

    public void fill (CharacterCreationDto dto) {
        characterCreationMapper.fill(dto);
        dto.setPlayerCharacter(playerCharacterFactory.getNewPlayerCharacter());
        //TODO PlayerClass einfügen/Karusel aus der CharacterCreation extrahieren und als Fragment verfügbar machen

        dto.setPointsDisplayMessageWPL(messageSource.getMessage("characterCreation.pointDisplay.withPlaceholder", null, LocaleContextHolder.getLocale()));
    }


}
