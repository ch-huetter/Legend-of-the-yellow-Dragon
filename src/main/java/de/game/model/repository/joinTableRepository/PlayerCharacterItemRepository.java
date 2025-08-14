package de.game.model.repository.joinTableRepository;

import de.game.model.entity.joinTable.PlayerCharacterItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCharacterItemRepository extends CrudRepository<PlayerCharacterItem, Integer> {
}
