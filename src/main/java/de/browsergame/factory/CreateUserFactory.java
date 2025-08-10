package de.browsergame.factory;

import de.browsergame.model.entity.User;
import de.browsergame.model.entity.joinTable.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateUserFactory {

    private final UserFactory userFactory;
    private final RoleFactory roleFactory;


    public User createAdminUser (String name, String password) {
        User newUser = userFactory.createDefaultUser(name, password);
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createPlayerRole()));
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createGamemasterRole()));
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createAdminRole()));
        return newUser;

    }

    public User createGamemasterUser (String name, String password) {
        User newUser = userFactory.createDefaultUser(name, password);
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createPlayerRole()));
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createGamemasterRole()));
        return newUser;
    }

    public User createPlayerUser (String name, String password) {
        User newUser = userFactory.createDefaultUser(name, password);
        newUser.getUserRoles().add(new UserRole(newUser, roleFactory.createPlayerRole()));
        return newUser;
    }

}
