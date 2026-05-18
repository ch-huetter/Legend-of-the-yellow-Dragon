package de.game.apiController.Sites.layout;

import de.game.service.filler.dto.DtoFillerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
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
