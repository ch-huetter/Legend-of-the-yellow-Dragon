package de.game.controller.site;

import de.game.controller.dto.LayoutDto;
import de.game.controller.dto.character.CharacterCreationDto;
import de.game.controller.dto.character.CharacterViewDto;
import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.model.repository.PlayerCharacterRepository;
import de.game.service.CalculateCharacterValuesService;
import de.game.service.UserService;
import de.game.service.filler.dto.DtoFillerService;
import de.game.service.initializer.PlayerCharacterInitializer;
import de.game.util.enums.ui.CharacterState;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.UnknownTypeException;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/backoffice/character")
public class CharacterCreationController {

    private final PlayerCharacterRepository playerCharacterRepository;
    private final DtoFillerService characterDtoFillerService;
    private final CalculateCharacterValuesService cCVS;
    private final PlayerCharacterInitializer playerCharacterInitializer;
    private final UserService userService;

    @GetMapping("/showCreate")
    public String showCharacterCreationScreen (@ModelAttribute("characterDto") CharacterCreationDto characterCreationDto, @ModelAttribute LayoutDto layoutDto,
                                               HttpServletRequest request, Model model) {
        User activeUser = userService.getLoggedInUserFromDb();
        if (playerCharacterRepository.countByUser(activeUser) >= activeUser.getMaxCharacters()) {
            return "redirect:/home";
        }

        characterDtoFillerService.fillDto(characterCreationDto);
        model.addAttribute("state", CharacterState.CREATE.name());
        return "sites/character";
    }

    @PostMapping("/create")
    public String createCharacter (@ModelAttribute("characterDto") @Valid CharacterCreationDto characterCreationDto, BindingResult bindingResult,
                                   @ModelAttribute LayoutDto layoutDto, HttpServletRequest request, Model model)
    throws UnknownTypeException {
        if (bindingResult.hasErrors()) {
            log.debug("characterCreationDto has errors. redirecting to creationPage");
            return showCharacterCreationScreen(characterCreationDto, layoutDto, request, model);
        }
        PlayerCharacter playerCharacter = playerCharacterInitializer.initAfterCreation(characterCreationDto);
        cCVS.calculateCharacterValues(playerCharacter);
        playerCharacterRepository.save(playerCharacter);

        User user = userService.getLoggedInUserFromDb();
        userService.updateUser(User.builder().id(user.getId()).activePlayerCharacter(characterCreationDto.getName()).build());
        return "redirect:/home";
    }

    @RolesAllowed("Administrator, Gamemaster")
    @GetMapping("showCharacter/{id}")
    public String showCharacterView (@ModelAttribute("characterDto") CharacterViewDto characterViewDto, @PathVariable String characterName, Model model) {
        //TODO Charakter aus Datenbank holen. Erst machbar wenn Charakter speichern funktioniert. Wird später für Administratoren über eine Charakter Liste verfügbar sein.
        //TODO Nur für Gamemaster/Administrator Role verfügbar machen
        model.addAttribute("state", CharacterState.VIEW);
        return "sites/character";
    }

    @GetMapping("showActive")
    public String showCharacterView (@ModelAttribute("characterDto") CharacterViewDto characterViewDto, Model model, HttpServletRequest req) {
        //TODO Charakter aus Datenbank holen. Erst machbar wenn Charakter speichern funktioniert
        characterDtoFillerService.fillDto(characterViewDto);
        model.addAttribute("state", CharacterState.VIEW.name());
        return "sites/character";
    }


}
