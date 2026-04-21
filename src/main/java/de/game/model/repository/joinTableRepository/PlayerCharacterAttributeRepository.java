package de.game.model.repository.joinTableRepository;

import de.game.model.entity.PlayerCharacter;
import de.game.model.entity.joinTable.PlayerCharacterAttribute;
import de.game.model.entity.primaryKeys.PlayerCharacterAttributeId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerCharacterAttributeRepository extends CrudRepository<PlayerCharacterAttribute, PlayerCharacterAttributeId> {
    public List<PlayerCharacterAttribute> findBycharacterName (PlayerCharacter playerCharacter);
}
