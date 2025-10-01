package de.game.controller.site;

import de.game.controller.dto.CharacterCreationDto;
import de.game.controller.dto.LayoutDto;
import de.game.model.entity.User;
import de.game.model.repository.PlayerCharacterRepository;
import de.game.service.CharacterCreationDtoFiller;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/character")
public class CharacterController {

    private final PlayerCharacterRepository playerCharacterRepository;
    private final CharacterCreationDtoFiller characterCreationDtoFiller;

    @GetMapping("/showCreate")
    public String showCharacterCreationScreen (@ModelAttribute CharacterCreationDto characterCreationDto, @ModelAttribute LayoutDto layoutDto, HttpServletRequest request) {
        User activeUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (playerCharacterRepository.countByUser(activeUser) >= activeUser.getMaxCharacters()) {
            return "redirect:@{/character}";
        }
        characterCreationDtoFiller.fill(characterCreationDto);

        return "sites/characterCreation";
    }

    @PostMapping("/create")
    public String createCharacter (@ModelAttribute CharacterCreationDto characterCreationDto, @ModelAttribute LayoutDto layoutDto) {
        log.info("createCharacter called recieved playerCharacter {}", characterCreationDto.getPlayerCharacter());
        if (characterCreationDto.getPlayerCharacter().getAttributes() != null) {
            characterCreationDto.getPlayerCharacter().getAttributes().values().forEach(attribute -> {
                log.info("Attribute {} Set with Value {}", attribute.getAttributeKey(), attribute.getValue());
            });
        } else {
            log.info("No Attributes Set. Upsi sth went wrong in Creation");
        }


        return "redirect:/home";
    }

}
