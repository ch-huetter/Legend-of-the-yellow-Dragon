package de.browsergame.model.repository.joinTableRepository;

import de.browsergame.model.entity.joinTable.PlayerCharacterItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCharacterItemRepository extends CrudRepository<PlayerCharacterItem, Integer> {
}
