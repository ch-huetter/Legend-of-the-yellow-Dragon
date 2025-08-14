package de.game.model.repository.joinTableRepository;

import de.game.model.entity.joinTable.PlayerCharacterEquipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCharacterEquipmentRepository extends CrudRepository<PlayerCharacterEquipment, Integer> {
}
