package de.browsergame.model.repository;

import de.browsergame.model.entity.AbilityTree;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityTreeRepository extends CrudRepository<AbilityTree, Integer> {
}
