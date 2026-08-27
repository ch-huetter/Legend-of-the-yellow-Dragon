package de.game.apiController.sites.layout;

import de.game.service.filler.dto.DtoFillerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
@Slf4j
public class MainLayoutApiController {

    private final DtoFillerService dtoFillerService;
    private final LayoutApiDtoFactory layoutApiDtoFactory;

    @GetMapping("/mainInit")
    public ResponseEntity<LayoutApiDto> initLayout () {
        LayoutApiDto layoutApiDto = layoutApiDtoFactory.createDto();
        dtoFillerService.fillDto(layoutApiDto);
        return ResponseEntity.ok().body(layoutApiDto);
    }

}
