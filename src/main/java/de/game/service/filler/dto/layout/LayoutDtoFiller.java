package de.game.service.filler.dto.layout;

import de.game.controller.dto.LayoutDto;
import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.service.UserService;
import de.game.service.filler.dto.DtoFiller;
import de.game.service.getter.PlayerCharacterGetter;
import de.game.util.basic.BasicEmptyCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LayoutDtoFiller implements DtoFiller<LayoutDto> {

    /*
    Subject to Change due to Future Changes to the Administration
     */

    private final UserService userService;
    private final PlayerCharacterGetter playerCharacterGetter;

    private void fillLayoutDtoWithPlayerValues (PlayerCharacter playerCharacter, LayoutDto layoutDto) {

        //ToDo Experience muss hier über die richtige Quelle eingefügt werden

        layoutDto.setPlayerCharacter(playerCharacter);
    }


    @Override
    public void fillDto (LayoutDto dto) {
        User            user                    = userService.getLoggedInUserFromDb();
        PlayerCharacter playerCharacter         = null;
        Integer         activePlayerCharacterId = user.getActivePlayerCharacterId();

        if (BasicEmptyCheck.isSet(activePlayerCharacterId)) {
            playerCharacter = playerCharacterGetter.getPlayerCharacterById(activePlayerCharacterId);
        }

        if (playerCharacter != null) {
            dto.setPlayerCharacter(playerCharacter);
        }
    }

    /**
     * @return
     */
    @Override
    public Class<LayoutDto> supports () {
        return LayoutDto.class;
    }
}
