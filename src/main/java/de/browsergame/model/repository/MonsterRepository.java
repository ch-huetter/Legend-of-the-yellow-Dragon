package de.browsergame.model.repository;

import de.browsergame.model.entity.Monster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonsterRepository extends CrudRepository<Monster, Integer> {
}
