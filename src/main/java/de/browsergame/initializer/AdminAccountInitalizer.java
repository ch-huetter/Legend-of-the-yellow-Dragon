package de.browsergame.initializer;

import de.browsergame.factory.CreateUserFactory;
import de.browsergame.factory.UserFactory;
import de.browsergame.model.entity.User;
import de.browsergame.model.repository.UserRepository;
import de.browsergame.model.repository.joinTableRepository.UserRoleRepository;
import de.browsergame.service.CreateUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Ensures that certain accounts for Administration are created.
 */
@Component
@Order(2)
@RequiredArgsConstructor
@Slf4j
public class AdminAccountInitalizer implements ApplicationRunner {

    private final CreateUserFactory createUserFactory;
    private final CreateUserService createUserService;

    @Override
    public void run(ApplicationArguments args){
        final String adminAccountPrefix = "ad_";
        log.info("Starting default User Check");

        createUserService.createUserWithRoles(createUserFactory.createPlayerUser(adminAccountPrefix + "Player", "rtscts"));
        createUserService.createUserWithRoles((createUserFactory.createGamemasterUser(adminAccountPrefix + "Gamemaster", "rtscts")));
        createUserService.createUserWithRoles((createUserFactory.createAdminUser(adminAccountPrefix + "Admin", "rtscts")));

        log.info("Default user check finished");

    }
}
