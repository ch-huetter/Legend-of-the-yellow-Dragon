package de.game.apiController.sites.characterSelection;

import de.game.bean.dto.PlayerCharacterDto;
import de.game.model.entity.PlayerCharacter;
import de.game.service.factory.livingEntity.PlayerCharacterDtoFactory;
import de.game.service.filler.dto.DtoFiller;
import de.game.service.sorter.PlayerCharacterListSorterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CharacterSelectionApiDtoFiller implements DtoFiller<CharacterSelectionApiDto> {

    private final PlayerCharacterListSorterService playerCharacterListSorterService;
    private final PlayerCharacterDtoFactory playerCharacterDtoFactory;

    @Override
    public void fillDto (CharacterSelectionApiDto dto) {
        List<PlayerCharacter>    sortedList             = playerCharacterListSorterService.removeActiveSortByLevel();
        List<PlayerCharacterDto> playerCharacterDtoList = new ArrayList<>();
        for (PlayerCharacter playerCharacter : sortedList) {
            playerCharacterDtoList.add(playerCharacterDtoFactory.createPlayerCharacterDto(playerCharacter));
        }
        dto.setPlayerCharacterList(playerCharacterDtoList);
    }

    @Override
    public Class<CharacterSelectionApiDto> supports () {
        return CharacterSelectionApiDto.class;
    }
}
