package de.game.apiController;

import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.service.UserService;
import de.game.service.getter.PlayerCharacterGetter;
import de.game.service.sorter.PlayerCharacterListSorterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game/character")
@Slf4j
public class CharacterApiController {

    private final UserService userService;
    private final PlayerCharacterGetter playerCharacterGetter;
    private final PlayerCharacterListSorterService playerCharacterListSorterService;

    @GetMapping("/getSelectableCharacters")
    public List<PlayerCharacter> getCharactersSortByActiveThenLevel () {
        return playerCharacterListSorterService.removeActiveSortByLevel();
    }

    @GetMapping("/getActiveCharacter")
    public PlayerCharacter getActivePlayerCharakter () {
        return playerCharacterGetter.getPlayerCharacterByUser(userService.getLoggedInUserFromDb());
    }

    @PostMapping("/setNewActiveCharacterByName")
    public ResponseEntity<NewActiveCharacterByNameResponse> setNewActiveCharacterByName (@RequestBody NewActiveCharacterByNameRequest changeRequest) {

        log.debug("characterName {}", changeRequest.characterName);
        PlayerCharacter playerCharacter = playerCharacterGetter.getPlayerCharacterById(changeRequest.characterName);
        User            user            = userService.getLoggedInUserFromDb();

        if (playerCharacter != null) {
            user.setActivePlayerCharacter(changeRequest.characterName);
            userService.updateUser(user);
        }
        return ResponseEntity.ok(new NewActiveCharacterByNameResponse(playerCharacter, playerCharacterListSorterService.removeActiveSortByLevel()));
    }

    public record NewActiveCharacterByNameRequest(String characterName) {
    }

    public record NewActiveCharacterByNameResponse(PlayerCharacter activeCharacter, List<PlayerCharacter> sortedCharacterList) {
    }


}
