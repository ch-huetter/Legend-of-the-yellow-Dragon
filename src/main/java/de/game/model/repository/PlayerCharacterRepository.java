package de.game.model.repository;

import de.game.model.entity.PlayerCharacter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCharacterRepository extends CrudRepository<PlayerCharacter, String> {
}
