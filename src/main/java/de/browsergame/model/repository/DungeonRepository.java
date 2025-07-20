package de.browsergame.model.repository;

import de.browsergame.model.entity.Dungeon;
import org.springframework.data.repository.CrudRepository;

public interface DungeonRepository extends CrudRepository<Dungeon, Integer> {
}
