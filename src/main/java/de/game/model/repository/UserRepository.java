package de.game.model.repository;

import de.game.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByloginName (String username);

    public Boolean existsByloginName (String username);

    boolean existsByeMail (@NotBlank String eMail);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userRoles ur LEFT JOIN FETCH ur.role Where u.loginName = :username")
    public Optional<User> findByloginNameWithUserRoles (String username);

}
