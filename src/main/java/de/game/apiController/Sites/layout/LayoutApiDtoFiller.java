package de.game.apiController.Sites.layout;

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
public class LayoutApiDtoFiller implements DtoFiller<LayoutApiDto> {

    private final UserService userService;
    private final PlayerCharacterGetter playerCharacterGetter;


    /**
     * @param dto
     */
    @Override
    public void fillDto (LayoutApiDto dto) {
        User            user                      = userService.getLoggedInUserFromDb();
        PlayerCharacter playerCharacter           = null;
        Integer         activePlayerCharacterName = user.getActivePlayerCharacterId();

        if (BasicEmptyCheck.isSet(activePlayerCharacterName)) {
            playerCharacter = playerCharacterGetter.getPlayerCharacterById(activePlayerCharacterName);
        }

        if (playerCharacter != null) {
            dto.setPlayerCharacter(playerCharacter);
        }
    }


    /**
     * @return Class
     */
    @Override
    public Class<LayoutApiDto> supports () {
        return LayoutApiDto.class;
    }
}
