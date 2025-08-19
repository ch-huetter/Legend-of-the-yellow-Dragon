package de.game.service;

import de.game.controller.dto.LayoutDto;
import de.game.model.entity.PlayerCharacter;
import de.game.view.uielements.Bar;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FillLayoutDtoService {

    private final MessageSource messageSource;

    public void fillLayoutDto (PlayerCharacter playerCharacter, LayoutDto layoutDto) {

        if (playerCharacter == null) {
            fillLayoutDtoWithDefaultValues(layoutDto);
        } else {
            fillLayoutDtoWithPlayerValues(playerCharacter, layoutDto);
        }
    }

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
        layoutDto.setGold(0);
        layoutDto.setLevel(0);
        layoutDto.setCharacterName(playerCharacter.getName());
    }
}
