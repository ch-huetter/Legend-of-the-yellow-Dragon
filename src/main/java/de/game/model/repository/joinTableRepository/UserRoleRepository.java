package de.game.model.repository.joinTableRepository;

import de.game.model.entity.Role;
import de.game.model.entity.User;
import de.game.model.entity.joinTable.UserRole;
import de.game.model.entity.primaryKeys.UserRoleId;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleId> {
    public void deleteByUser (User user);

    public void deleteByRole (Role role);

    public void findByUser (User user);
}
