package de.game.controller;

import de.game.controller.dto.LayoutDto;
import de.game.service.FillLayoutDtoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final FillLayoutDtoService fillLayoutDtoService;

    @GetMapping("/login")
    public String login (@ModelAttribute LayoutDto layoutDto) {

        fillLayoutDtoService.fillLayoutDto(null, layoutDto);

        return "login/login";
    }
}
