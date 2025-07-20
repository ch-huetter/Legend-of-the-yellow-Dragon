package de.browsergame.model.repository;

import de.browsergame.model.entity.PlayerCharacter;
import org.springframework.data.repository.CrudRepository;

public interface PlayerCharacterRepository extends CrudRepository<PlayerCharacter, String> {
}
