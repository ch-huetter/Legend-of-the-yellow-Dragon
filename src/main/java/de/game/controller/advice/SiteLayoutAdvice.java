package de.game.controller.advice;

import de.game.controller.dto.LayoutDto;
import de.game.service.filler.dto.layout.LayoutDtoFiller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@Slf4j
@ControllerAdvice(basePackages = "de.game.controller.site")
@RequiredArgsConstructor
public class SiteLayoutAdvice {

    private final LayoutDtoFiller layoutDtoFiller;

    @ModelAttribute()
    public LayoutDto addLayoutDto () {
        LayoutDto dto = new LayoutDto();
        layoutDtoFiller.fillDto(dto);
        return dto;
    }
}
