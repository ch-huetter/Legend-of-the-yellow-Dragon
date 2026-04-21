package de.game.controller.site;

import de.game.controller.dto.HomeDto;
import de.game.controller.dto.LayoutDto;
import de.game.model.entity.User;
import de.game.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/backoffice")
public class HomeController {

    private final MessageSource messageSource;
    private final UserService userService;

    @GetMapping(value = {"/home", "/"})
    public String showLandingPage (@ModelAttribute HomeDto homeDto, @ModelAttribute LayoutDto layoutDto, HttpServletRequest request) {
        User activeUser = userService.getLoggedInUserFromDb();
        if (activeUser.getActivePlayerCharacter() == null || activeUser.getActivePlayerCharacter().isBlank()) {
            layoutDto.setCharacterName(messageSource.getMessage("phrase.noCharacterAvailable", null, LocaleContextHolder.getLocale()));
            homeDto.getMessages().add(messageSource.getMessage("message.noCharacterAvailable", null, LocaleContextHolder.getLocale()));
        }
        return "sites/home";
    }
}
