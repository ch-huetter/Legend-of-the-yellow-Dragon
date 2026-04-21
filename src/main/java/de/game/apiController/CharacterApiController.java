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
@RequestMapping("/api/character")
@Slf4j
public class CharacterApiController {

    private final UserService userService;
    private final PlayerCharacterGetter playerCharacterGetter;
    private final PlayerCharacterListSorterService playerCharacterListSorterService;

    @GetMapping("/getUserCharactersSortedByActiveThenLevel")
    public List<PlayerCharacter> getCharactersSortByActiveThenLevel () {
        return playerCharacterListSorterService.sortByActiveThenLevel();
    }

    @PostMapping("/setNewActiveCharacterByName")
    public ResponseEntity<String> setNewActiveCharacterByName (@RequestBody NewActiveCharacterByNameRequest changeRequest) {

        log.info("characterName {}", changeRequest.characterName);
        PlayerCharacter playerCharacter = playerCharacterGetter.getPlayerCharacterById(changeRequest.characterName);
        User            user            = userService.getLoggedInUserFromDb();

        if (playerCharacter != null) {
            user.setActivePlayerCharacter(changeRequest.characterName);
            userService.updateUser(user);
        }
        return ResponseEntity.ok("ok");
    }

    public record NewActiveCharacterByNameRequest(String characterName) {
    }
}
