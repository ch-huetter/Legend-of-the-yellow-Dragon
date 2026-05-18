package de.game.apiController.Sites.characterSelection;

import de.game.service.filler.dto.DtoFillerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/game/characterSelection")
@Controller
@RequiredArgsConstructor
public class CharacterSelectionApiController {

    private final CharacterSelectionApiDtoFactory characterSelectionApiDtoFactory;
    private final DtoFillerService dtoFillerService;

    @GetMapping("/init")
    public ResponseEntity<CharacterSelectionApiDto> innit () {
        CharacterSelectionApiDto dto = characterSelectionApiDtoFactory.createDto();
        dtoFillerService.fillDto(dto);
        return ResponseEntity.ok(dto);
    }
}
