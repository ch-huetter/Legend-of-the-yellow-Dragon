package de.game.model.repository;

import de.game.model.entity.Effect;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EffectRepository extends CrudRepository<Effect, String> {
}
