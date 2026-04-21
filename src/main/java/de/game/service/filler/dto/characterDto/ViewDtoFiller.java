package de.game.service.filler.dto.characterDto;

import de.game.controller.dto.character.CharacterViewDto;
import de.game.model.repository.PlayerCharacterRepository;
import de.game.service.filler.dto.DtoFiller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewDtoFiller implements DtoFiller<CharacterViewDto> {

    private final PlayerCharacterRepository playerCharacterRepository;

    /**
     * @param dto
     */
    @Override
    public void fillDto (CharacterViewDto dto) {

    }

    /**
     * @return CharacterViewDto.class
     */
    @Override
    public Class<CharacterViewDto> supports () {
        return CharacterViewDto.class;
    }
}
