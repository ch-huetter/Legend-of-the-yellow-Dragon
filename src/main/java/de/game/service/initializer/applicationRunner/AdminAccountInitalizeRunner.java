package de.game.service.initializer.applicationRunner;

import de.game.service.CreateUserService;
import de.game.service.factory.CreateUserFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Ensures that certain accounts for Administration are created.
 */
@Component
@Order(2)
@RequiredArgsConstructor
@Slf4j
public class AdminAccountInitalizeRunner implements ApplicationRunner {

    private final CreateUserFactory createUserFactory;
    private final CreateUserService createUserService;

    @Override
    public void run (ApplicationArguments args) {
        final String adminAccountPrefix = "ad_";
        log.info("Starting default User Check");

        createUserService.checkUserIntegrity(createUserFactory.createPlayerUser(adminAccountPrefix + "Player", "rtscts"));
        createUserService.checkUserIntegrity((createUserFactory.createGamemasterUser(adminAccountPrefix + "Gamemaster", "rtscts")));
        createUserService.checkUserIntegrity((createUserFactory.createAdminUser(adminAccountPrefix + "Admin", "rtscts")));

        log.info("Default user check finished");

    }
}
