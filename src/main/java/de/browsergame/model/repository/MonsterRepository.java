package de.browsergame.model.repository;

import de.browsergame.model.entity.Monster;
import org.springframework.data.repository.CrudRepository;

public interface MonsterRepository extends CrudRepository<Monster, Integer> {
}
