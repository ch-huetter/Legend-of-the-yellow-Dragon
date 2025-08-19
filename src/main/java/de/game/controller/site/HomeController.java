package de.game.controller.site;

import de.game.controller.dto.HomeDto;
import de.game.controller.dto.LayoutDto;
import de.game.model.entity.User;
import de.game.model.repository.PlayerCharacterRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PlayerCharacterRepository playerCharacterRepository;
    private final MessageSource messageSource;

    @GetMapping(value = {"/home", "/"})
    public String showLandingPage (@ModelAttribute HomeDto homeDto, @ModelAttribute LayoutDto layoutDto, HttpServletRequest request) {
        log.info("HomeController called showing home.html");
        User activeUser = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (activeUser.getActivePlayerCharacter() == null || activeUser.getActivePlayerCharacter().isBlank()) {
            layoutDto.setCharacterName(messageSource.getMessage("phrase.noCharacterAvailable", null, LocaleContextHolder.getLocale()));
            homeDto.getMessages().add(messageSource.getMessage("message.noCharacterAvailable", null, LocaleContextHolder.getLocale()));
        }
        return "sites/home";
    }
}
