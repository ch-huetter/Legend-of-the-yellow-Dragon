package de.browsergame.initializer;

import de.browsergame.enums.RoleEnum;
import de.browsergame.factory.UserRoleFactory;
import de.browsergame.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountInitializer {

    private final UserRoleFactory userRoleFactory;

    public void initializePlayerAccount (User userToInitialize) {
        userToInitialize.setActive(true);
        userToInitialize.getUserRoles().add(userRoleFactory.createUserRole(userToInitialize, RoleEnum.PLAYER));
    }
}
