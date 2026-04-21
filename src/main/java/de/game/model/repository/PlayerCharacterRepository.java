package de.game.model.repository;

import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Dont use it to get PlayerCharacters Directly. When you need a playerCharacter use the PlayerCharacterGetter instead.
 */

@Repository
public interface PlayerCharacterRepository extends CrudRepository<PlayerCharacter, String> {
    public Integer countByUser (User user);

    public List<PlayerCharacter> findByUser (User user);
    
    public boolean existsByName (String name);
}
