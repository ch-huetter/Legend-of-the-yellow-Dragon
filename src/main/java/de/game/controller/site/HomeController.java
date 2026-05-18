package de.game.controller.site;

import de.game.controller.dto.HomeDto;
import de.game.controller.dto.LayoutDto;
import de.game.service.UserService;
import de.game.service.filler.dto.DtoFillerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
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
    private final DtoFillerService dtoFillerService;

    @GetMapping(value = {"/home", "/"})
    public String showLandingPage (@ModelAttribute HomeDto homeDto, @ModelAttribute LayoutDto layoutDto, HttpServletRequest request) {
        return "sites/home";
    }
}
