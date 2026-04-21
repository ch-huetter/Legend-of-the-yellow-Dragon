package de.game.service.filler.dto.layout;

import de.game.controller.dto.LayoutDto;
import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.service.UserService;
import de.game.service.filler.dto.DtoFiller;
import de.game.service.getter.PlayerCharacterGetter;
import de.game.view.uielements.Bar;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LayoutDtoFiller implements DtoFiller<LayoutDto> {

    private final MessageSource messageSource;
    private final UserService userService;
    private final PlayerCharacterGetter playerCharacterGetter;

    private void fillLayoutDtoWithDefaultValues (LayoutDto layoutDto) {
        layoutDto.setHealthBar(new Bar(0, 0, messageSource.getMessage("bar.health", null, LocaleContextHolder.getLocale()), "health"));
        layoutDto.setEnergyBar(new Bar(0, 0, messageSource.getMessage("bar.energy", null, LocaleContextHolder.getLocale()), "energy"));
        layoutDto.setExperienceBar(new Bar(0, 0, messageSource.getMessage("bar.experience", null, LocaleContextHolder.getLocale()), "experience"));
        layoutDto.setGold(0);
        layoutDto.setLevel(0);
    }

    private void fillLayoutDtoWithPlayerValues (PlayerCharacter playerCharacter, LayoutDto layoutDto) {

        layoutDto.setHealthBar(new Bar(playerCharacter.getHealth(), playerCharacter.getMaxHealth(), messageSource.getMessage("bar.health", null, LocaleContextHolder.getLocale()),
                                       "health"));
        layoutDto.setEnergyBar(new Bar(playerCharacter.getMaxMana(), playerCharacter.getMaxMana(), messageSource.getMessage("bar.energy", null, LocaleContextHolder.getLocale()),
                                       "energy"));
        //ToDo Experience muss hier über die richtige Quelle eingefügt werden
        layoutDto.setExperienceBar(
                new Bar(playerCharacter.getExperience(), 10000, messageSource.getMessage("bar.experience", null, LocaleContextHolder.getLocale()), "experience"));
        layoutDto.setGold(playerCharacter.getGold());
        layoutDto.setLevel(Integer.valueOf(playerCharacter.getLevel()));
        layoutDto.setCharacterName(playerCharacter.getName());
    }


    @Override
    public void fillDto (LayoutDto dto) {
        User            user            = userService.getLoggedInUserFromDb();
        PlayerCharacter playerCharacter = null;
        if (user.getActivePlayerCharacter() != null) {
            playerCharacter = playerCharacterGetter.getPlayerCharacterById(user.getActivePlayerCharacter());
        }

        if (playerCharacter == null) {
            fillLayoutDtoWithDefaultValues(dto);
        } else {
            fillLayoutDtoWithPlayerValues(playerCharacter, dto);
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
