package de.browsergame.model.repository;

import de.browsergame.model.entity.PlayerCharacter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCharacterRepository extends CrudRepository<PlayerCharacter, String> {
}
