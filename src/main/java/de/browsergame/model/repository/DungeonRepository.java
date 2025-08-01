package de.browsergame.model.repository;

import de.browsergame.model.entity.Dungeon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DungeonRepository extends CrudRepository<Dungeon, Integer> {
}
