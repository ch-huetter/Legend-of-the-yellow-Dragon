package de.browsergame.model.repository.joinTableRepository;

import de.browsergame.model.entity.Role;
import de.browsergame.model.entity.User;
import de.browsergame.model.entity.joinTable.UserRole;
import de.browsergame.model.entity.primaryKeys.UserRoleId;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleId> {
    public void deleteByUser(User user);
    public void deleteByRole(Role role);
    public void findByUser(User user);
}
