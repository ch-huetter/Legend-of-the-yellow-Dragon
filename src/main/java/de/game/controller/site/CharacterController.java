package de.game.controller.site;

import de.game.controller.dto.CharacterCreationDto;
import de.game.controller.dto.LayoutDto;
import de.game.model.entity.User;
import de.game.model.repository.PlayerCharacterRepository;
import de.game.service.factory.PlayerCharacterFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/Character")
public class CharacterController {

    private final PlayerCharacterRepository playerCharacterRepository;
    private final PlayerCharacterFactory playerCharacterFactory;

    @GetMapping("/create")
    public String showCharacterCreationScreen (@ModelAttribute CharacterCreationDto characterCreationDto, @ModelAttribute LayoutDto layoutDto) {
        User activeUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (playerCharacterRepository.countByUser(activeUser) >= activeUser.getMaxCharacters()) {
            return "redirect:@{/character}";
        }

        characterCreationDto.setPlayerCharacter(playerCharacterFactory.getNewPlayerCharacter());

        return "sites/characterCreation";
    }

}
