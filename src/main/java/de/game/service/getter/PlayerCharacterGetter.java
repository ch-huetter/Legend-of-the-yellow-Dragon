package de.game.service.getter;


import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import de.game.model.repository.PlayerCharacterRepository;
import de.game.model.repository.joinTableRepository.PlayerCharacterAttributeRepository;
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

    @Transactional
    public PlayerCharacter getPlayerCharacterById (String Name) {
        Optional<PlayerCharacter> playerCharacter = playerCharacterRepository.findById(Name);
        return playerCharacter.orElse(null);
    }

    @Transactional
    public List<PlayerCharacter> getPlayerCharacterByUser (User user) {
        return playerCharacterRepository.findByUser(user);
    }

}
