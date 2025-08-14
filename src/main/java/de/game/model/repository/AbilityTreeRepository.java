package de.game.model.repository;

import de.game.model.entity.AbilityTree;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityTreeRepository extends CrudRepository<AbilityTree, Integer> {
}
