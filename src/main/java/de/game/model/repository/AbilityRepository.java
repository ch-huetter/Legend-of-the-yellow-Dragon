package de.game.model.repository;

import de.game.model.entity.Ability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityRepository extends CrudRepository<Ability, String> {

}
