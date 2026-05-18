package de.game.service.getter;


import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.model.repository.PlayerCharacterRepository;
import de.game.model.repository.joinTableRepository.PlayerCharacterAttributeRepository;
import de.game.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerCharacterGetter {

    private final PlayerCharacterRepository playerCharacterRepository;
    private final PlayerCharacterAttributeRepository playerCharacterAttributeRepository;
    private final UserService userService;

    @Transactional
    private PlayerCharacter getPlayerCharacter (String name) {
        Optional<PlayerCharacter> playerCharacter = playerCharacterRepository.findById(name);
        return playerCharacter.orElse(null);
    }

    public PlayerCharacter getPlayerCharacterById (String name) {
        PlayerCharacter playerCharacter = getPlayerCharacter(name);
        if (playerCharacter == null) {
            throw new NullPointerException("No Character found for " + name);
        } else {
            return playerCharacter;
        }
    }


    @Transactional
    public List<PlayerCharacter> getPlayerCharactersByUser (User user) {
        return playerCharacterRepository.findByUser(user);
    }

    public PlayerCharacter getPlayerCharacterByUser (User user) {
        return playerCharacterRepository.findById(user.getActivePlayerCharacter()).orElseThrow(NullPointerException::new);
    }

    public PlayerCharacter getActivePlayerCharacter () {
        User user = userService.getLoggedInUserFromDb();
        return playerCharacterRepository.findById(user.getActivePlayerCharacter()).orElseThrow(NullPointerException::new);
    }

}
