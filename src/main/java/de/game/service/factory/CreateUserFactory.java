package de.game.service.factory;

import de.game.model.entity.User;
import de.game.model.entity.joinTable.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserFactory {

    private final UserFactory userFactory;
    private final RoleFactory roleFactory;

    public User createAdminUser () {
        User newUser = userFactory.createDefaultUser();
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createPlayerRole()));
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createGamemasterRole()));
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createAdminRole()));
        return newUser;

    }

    public User createGamemasterUser () {
        User newUser = userFactory.createDefaultUser();
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createPlayerRole()));
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createGamemasterRole()));
        return newUser;
    }

    public User createPlayerUser () {
        User newUser = userFactory.createDefaultUser();
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createPlayerRole()));
        return newUser;
    }

}
