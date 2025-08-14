package de.game.controller.site;

import de.game.controller.dto.HomeDto;
import de.game.controller.dto.LayoutDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
public class HomeController {


    @GetMapping(value = {"/home", "/"})
    public String showLandingPage (@ModelAttribute HomeDto homeDto, @ModelAttribute LayoutDto layoutDto, Model model, HttpServletRequest request) {
        log.info("HomeController called showing home.html");
        layoutDto.getMessages().add("Hallo Welt");
        layoutDto.getMessages().add("Test Test");
        log.info("LayoutDto data = {}", layoutDto.toString());
        return "sites/index";
    }
}
