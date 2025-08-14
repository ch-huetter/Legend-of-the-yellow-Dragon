package de.game.service.initializer;

import de.game.model.entity.User;
import de.game.service.factory.UserRoleFactory;
import de.game.util.enums.RoleEnum;
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
