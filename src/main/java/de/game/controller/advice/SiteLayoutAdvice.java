package de.game.controller.advice;

import de.game.controller.dto.LayoutDto;
import de.game.model.entity.PlayerCharacter;
import de.game.service.FillLayoutDtoService;
import de.game.util.enums.SessionAttributeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@Slf4j
@ControllerAdvice(basePackages = "de.game.controller.site")
@RequiredArgsConstructor
public class SiteLayoutAdvice {

    private final FillLayoutDtoService fillLayoutDtoService;

    @ModelAttribute()
    public LayoutDto addLayout (HttpServletRequest req, HttpServletResponse res) {
        log.debug("Preparing LayoutDto for further use");
        LayoutDto       dto             = new LayoutDto();
        PlayerCharacter playerCharacter = SessionAttributeEnum.PLAYER_CHARACTER.get(req.getSession());
        log.debug("Filling layoutDto {} playerCharacter Data", playerCharacter == null ? "without" : "with");

        fillLayoutDtoService.fillLayoutDto(playerCharacter, dto);

        return dto;
    }
}
