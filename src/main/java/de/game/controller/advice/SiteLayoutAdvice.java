package de.game.controller.advice;

import de.game.controller.dto.LayoutDto;
import de.game.model.entity.PlayerCharacter;
import de.game.model.repository.PlayerCharacterRepository;
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

    private final PlayerCharacterRepository playerCharacterRepository;
    private final FillLayoutDtoService fillLayoutDtoService;

    @ModelAttribute()
    public LayoutDto addLayout (HttpServletRequest req, HttpServletResponse res) {
        LayoutDto       dto                 = new LayoutDto();
        String          playerCharakterName = SessionAttributeEnum.PLAYER_CHARACTER_NAME.get(req.getSession());
        PlayerCharacter playerCharacter     = null;
        if (playerCharakterName != null) {
            playerCharacter = playerCharacterRepository.findById(playerCharakterName).orElse(null);
        }

        fillLayoutDtoService.fillLayoutDto(playerCharacter, dto);

        return dto;
    }
}
