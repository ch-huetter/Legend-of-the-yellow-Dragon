package de.browsergame.model.repository;

import org.springframework.data.repository.CrudRepository;
import de.browsergame.model.entity.User;

public interface UserRepsitory extends CrudRepository<User,Integer> {

}
