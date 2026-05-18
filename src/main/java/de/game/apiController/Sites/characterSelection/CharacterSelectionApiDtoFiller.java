package de.game.apiController.Sites.characterSelection;

import de.game.service.filler.dto.DtoFiller;
import de.game.service.sorter.PlayerCharacterListSorterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterSelectionApiDtoFiller implements DtoFiller<CharacterSelectionApiDto> {

    private final PlayerCharacterListSorterService playerCharacterListSorterService;

    @Override
    public void fillDto (CharacterSelectionApiDto dto) {
        dto.setPlayerCharacterList(playerCharacterListSorterService.removeActiveSortByLevel());
    }

    @Override
    public Class<CharacterSelectionApiDto> supports () {
        return CharacterSelectionApiDto.class;
    }
}
