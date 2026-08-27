package de.game.apiController.sites.characterCreation;

import de.game.bean.frontendComponents.AttributeSelectionEntry;
import de.game.service.PlayerCharacterCreationService;
import de.game.service.filler.dto.DtoFillerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/game/characterCreation")
public class CharacterCreationApiController {

    private final CharacterCreationApiDtoFactory characterCreationApiDtoFactory;
    private final DtoFillerService dtoFillerService;
    private final PlayerCharacterCreationService playerCharacterCreationService;

    @GetMapping("/init")
    public ResponseEntity<CharacterCreationApiDto> init () {
        CharacterCreationApiDto characterCreationApiDto = characterCreationApiDtoFactory.createDto();
        dtoFillerService.fillDto(characterCreationApiDto);
        return ResponseEntity.ok(characterCreationApiDto);
    }

    @PostMapping("submitCharacterCreationRequest")
    public ResponseEntity<CharacterCreationResponse> processCharacterCreationRequest (@RequestBody CharacterCreationRequest characterCreationRequest) {
        log.info(characterCreationRequest.toString());
        //TODO Validation und ErrorHandling
        playerCharacterCreationService.createCharacter(characterCreationRequest);

        return ResponseEntity.ok(new CharacterCreationResponse());
    }

    public record CharacterCreationRequest(String name, List<AttributeSelectionEntry> attributes, Integer activePlayerClass) {
    }

    public record CharacterCreationResponse() {
    }

}
