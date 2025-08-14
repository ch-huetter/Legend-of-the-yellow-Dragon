package de.game.controller.site;

import de.game.controller.dto.CharacterCreationDto;
import de.game.controller.dto.LayoutDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/createCharacter")
public class CharacterCreationController {

    @GetMapping("")
    public String showCharacterCreationScreen (@ModelAttribute CharacterCreationDto characterCreationDto, @ModelAttribute LayoutDto layoutDto) {
        layoutDto.setHideHeader(true);

        return "sites/characterCreation";
    }

}
