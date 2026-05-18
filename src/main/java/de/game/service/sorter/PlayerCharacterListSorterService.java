package de.game.service.sorter;

import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.service.UserService;
import de.game.service.getter.PlayerCharacterGetter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlayerCharacterListSorterService {

    private final UserService userService;
    private final PlayerCharacterGetter playerCharacterGetter;

    public List<PlayerCharacter> removeActiveSortByLevel () {
        User                  user         = userService.getLoggedInUserFromDb();
        List<PlayerCharacter> unsortedList = playerCharacterGetter.getPlayerCharactersByUser(user);
        List<PlayerCharacter> sortedList   = new ArrayList<>();
        
        PlayerCharacter activePlayerCharacter =
                unsortedList.stream().filter(playerCharacter -> playerCharacter.getName().equals(user.getActivePlayerCharacter())).findFirst()
                        .orElseThrow(NullPointerException::new);

        unsortedList.remove(activePlayerCharacter);

        PlayerCharacter unsortedCharacter = null;
        int             currentLevel      = -1;

        for (int x = unsortedList.size(); x > 0; x--) {
            for (PlayerCharacter playerCharacterFromList : unsortedList) {
                if (playerCharacterFromList.getLevel() > currentLevel) {
                    currentLevel = playerCharacterFromList.getLevel();
                    unsortedCharacter = playerCharacterFromList;
                }
            }
            unsortedList.remove(unsortedCharacter);
            sortedList.add(unsortedCharacter);
            currentLevel = -1;
        }

        return sortedList;
    }


}
