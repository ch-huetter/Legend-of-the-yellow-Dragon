package de.browsergame.model.repository;

import de.browsergame.model.entity.AbilityRestriction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRestrictionRepository extends CrudRepository<AbilityRestriction, Integer> {
}
