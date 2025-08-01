package de.browsergame.model.repository;

import de.browsergame.model.entity.Attribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeRepository extends CrudRepository<Attribute, String> {

}
