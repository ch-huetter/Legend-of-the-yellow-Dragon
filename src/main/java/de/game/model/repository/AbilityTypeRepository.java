package de.game.model.repository;

import de.game.model.entity.AbilityType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityTypeRepository extends CrudRepository<AbilityType, Integer> {
}
