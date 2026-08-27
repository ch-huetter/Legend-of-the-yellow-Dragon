package de.game.apiController.sites.tavern;

import de.game.bean.RestPriceGetter;
import de.game.bean.dto.PlayerCharacterDto;
import de.game.bean.enums.ResponseStatus;
import de.game.model.entity.PlayerCharacter;
import de.game.model.repository.PlayerCharacterRepository;
import de.game.service.factory.livingEntity.PlayerCharacterDtoFactory;
import de.game.service.filler.dto.DtoFillerService;
import de.game.service.getter.PlayerCharacterGetter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/tavern")
public class TavernApiController {

    private final TavernApiDtoFactory tavernApiDtoFactory;
    private final DtoFillerService dtoFillerService;
    private final PlayerCharacterGetter playerCharacterGetter;
    private final PlayerCharacterRepository playerCharacterRepository;
    private final RestPriceGetter restPriceGetter;
    private final PlayerCharacterDtoFactory playerCharacterDtoFactory;

    @GetMapping("/init")
    public ResponseEntity<TavernApiDto> init () {
        TavernApiDto tavernApiDto = tavernApiDtoFactory.createDto();
        dtoFillerService.fillDto(tavernApiDto);
        return ResponseEntity.ok(tavernApiDto);
    }

    @PostMapping("rest")
    public ResponseEntity<RestResponse> rest () {
        PlayerCharacter playerCharacter = playerCharacterGetter.getActivePlayerCharacter();

        if (playerCharacter.getCurrentHealth().equals(playerCharacter.getMaxHealth())) {
            return ResponseEntity.ok(new RestResponse(ResponseStatus.ERROR, RestErrors.FULL_HEALTH, null));
        }

        if (playerCharacter.getGold() < restPriceGetter.getRestPrice()) {
            return ResponseEntity.ok(new RestResponse(ResponseStatus.ERROR, RestErrors.INSUFFICIENT_GOLD, null));
        }

        playerCharacter.setCurrentHealth(playerCharacter.getMaxHealth());
        playerCharacter.setGold(playerCharacter.getGold() - restPriceGetter.getRestPrice());
        playerCharacterRepository.save(playerCharacter);
        return ResponseEntity.ok(new RestResponse(ResponseStatus.SUCCESS, null, playerCharacterDtoFactory.createPlayerCharacterDto(playerCharacter)));
    }

    public record RestResponse(ResponseStatus status, RestErrors error, PlayerCharacterDto playerCharacterDto) {
    }

    public enum RestErrors {
        INSUFFICIENT_GOLD,
        FULL_HEALTH
    }

}
