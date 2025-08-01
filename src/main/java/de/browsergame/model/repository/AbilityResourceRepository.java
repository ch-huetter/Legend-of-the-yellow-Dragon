package de.browsergame.model.repository;

import de.browsergame.model.entity.AbilityResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityResourceRepository extends CrudRepository<AbilityResource, Integer> {
}
