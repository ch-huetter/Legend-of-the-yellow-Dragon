package de.browsergame.factory;

import de.browsergame.enums.RoleEnum;
import de.browsergame.model.entity.User;
import de.browsergame.model.entity.joinTable.UserRole;
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
