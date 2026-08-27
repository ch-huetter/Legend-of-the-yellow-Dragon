package de.game.apiController.sites.layout;

import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.service.UserService;
import de.game.service.factory.livingEntity.PlayerCharacterDtoFactory;
import de.game.service.filler.dto.DtoFiller;
import de.game.service.getter.PlayerCharacterGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LayoutApiDtoFiller implements DtoFiller<LayoutApiDto> {

    private final UserService userService;
    private final PlayerCharacterGetter playerCharacterGetter;
    private final PlayerCharacterDtoFactory playerCharacterDtoFactory;

    @Override
    public void fillDto (LayoutApiDto dto) {
        User    user                    = userService.getLoggedInUserFromDb();
        Integer activePlayerCharacterId = user.getActivePlayerCharacterId();

        if (activePlayerCharacterId != null) {
            PlayerCharacter playerCharacter = playerCharacterGetter.getPlayerCharacterById(activePlayerCharacterId);
            dto.setPlayerCharacterDto(playerCharacterDtoFactory.createPlayerCharacterDto(playerCharacter));
        } else {
            dto.setPlayerCharacterDto(null);
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
