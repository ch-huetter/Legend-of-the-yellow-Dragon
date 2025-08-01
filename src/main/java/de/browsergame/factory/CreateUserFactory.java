package de.browsergame.factory;

import de.browsergame.model.entity.Role;
import de.browsergame.service.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CreateUserFactory {

    private final UserFactory userFactory;
    private final RoleFactory roleFactory;


    public CreateUserService.UserWithRolesForCreation createAdminUser (String name, String password){
        Set<Role> roles = new HashSet<>();
        roles.add(roleFactory.createAdminRole());
        roles.add(roleFactory.createGamemasterRole());
        roles.add(roleFactory.createPlayerRole());
        return new CreateUserService.UserWithRolesForCreation(userFactory.createDefaultUser(name, password), roles);

    }

    public CreateUserService.UserWithRolesForCreation createGamemasterUser (String name, String password){
        Set<Role> roles = new HashSet<>();
        roles.add(roleFactory.createPlayerRole());
        roles.add(roleFactory.createGamemasterRole());
        return new CreateUserService.UserWithRolesForCreation(userFactory.createDefaultUser(name, password), roles);
    }

    public CreateUserService.UserWithRolesForCreation createPlayerUser (String name, String password) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleFactory.createPlayerRole());
        return new CreateUserService.UserWithRolesForCreation(userFactory.createDefaultUser(name, password), roles);
    }

}
