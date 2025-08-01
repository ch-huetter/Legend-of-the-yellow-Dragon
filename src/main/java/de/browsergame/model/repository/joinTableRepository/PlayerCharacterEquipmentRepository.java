package de.browsergame.model.repository.joinTableRepository;

import de.browsergame.model.entity.joinTable.PlayerCharacterEquipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCharacterEquipmentRepository extends CrudRepository<PlayerCharacterEquipment, Integer> {
}
