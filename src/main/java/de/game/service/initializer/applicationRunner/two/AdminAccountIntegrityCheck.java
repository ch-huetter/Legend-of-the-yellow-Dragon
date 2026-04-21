package de.game.service.initializer.applicationRunner.two;

import de.game.model.entity.User;
import de.game.service.UserService;
import de.game.service.factory.CreateUserFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
public class AdminAccountIntegrityCheck implements ApplicationRunner {

    private final CreateUserFactory createUserFactory;
    private final UserService userService;
    @Value("${application.custom.default.adminAccountPrefix}")
    private String adminAccountPrefix;

    @Override
    public void run (ApplicationArguments args) {

        final String defaultPassword = "rtscts";

        log.info("Starting default User Check");

        final User playerUser = createUserFactory.createPlayerUser();
        playerUser.setLoginName(adminAccountPrefix + "player");
        playerUser.setPassword(defaultPassword);
        playerUser.setEMail("test@mail.de");

        final User gmUser = createUserFactory.createGamemasterUser();
        gmUser.setLoginName(adminAccountPrefix + "gamemaster");
        gmUser.setPassword(defaultPassword);
        gmUser.setEMail("test@mail.de");

        final User adminUser = createUserFactory.createAdminUser();
        adminUser.setLoginName(adminAccountPrefix + "admin");
        adminUser.setPassword(defaultPassword);
        adminUser.setEMail("test@mail.de");

        userService.checkUserIntegrity(playerUser);
        userService.checkUserIntegrity((gmUser));
        userService.checkUserIntegrity((adminUser));

        log.info("Default user check finished");

    }
}
