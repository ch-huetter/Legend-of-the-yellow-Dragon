package de.browsergame.model.repository;

import de.browsergame.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import de.browsergame.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByloginName(String username);

}
