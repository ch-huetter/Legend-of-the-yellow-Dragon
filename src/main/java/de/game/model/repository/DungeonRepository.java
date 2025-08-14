package de.game.model.repository;

import de.game.model.entity.Dungeon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DungeonRepository extends CrudRepository<Dungeon, Integer> {
}
