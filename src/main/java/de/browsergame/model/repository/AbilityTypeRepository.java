package de.browsergame.model.repository;

import de.browsergame.model.entity.AbilityType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityTypeRepository extends CrudRepository<AbilityType, Integer> {
}
