package de.browsergame.model.repository;

import de.browsergame.model.entity.Configuration;
import org.springframework.data.repository.CrudRepository;

public interface ConfigurationRepository extends CrudRepository<Configuration, String> {
}
