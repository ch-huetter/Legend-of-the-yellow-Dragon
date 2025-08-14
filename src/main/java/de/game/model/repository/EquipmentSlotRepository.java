package de.game.model.repository;

import de.game.model.entity.EquipmentSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentSlotRepository extends CrudRepository<EquipmentSlot, Integer> {
}
