package de.game.service.factory;

import de.game.model.entity.User;
import de.game.model.entity.joinTable.UserRole;
import de.game.model.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRoleFactory {

    private final RoleFactory roleFactory;

    public UserRole createUserRole (User user, RoleEnum role) {
        UserRole userRole = new UserRole();
        userRole.setRole(roleFactory.getRoleForEnumEntry(role));
        userRole.setUser(user);
        return userRole;
    }

}
