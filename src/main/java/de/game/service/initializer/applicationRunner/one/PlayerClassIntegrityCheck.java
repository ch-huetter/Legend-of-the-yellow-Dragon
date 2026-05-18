package de.game.service.initializer.applicationRunner.one;

import de.game.model.entity.PlayerClass;
import de.game.model.enums.PlayerClassEnum;
import de.game.model.repository.PlayerClassRepository;
import de.game.service.factory.EnumToObjectFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(1)
public class PlayerClassIntegrityCheck implements ApplicationRunner {

    private final PlayerClassRepository playerClassRepository;
    private final EnumToObjectFactory enumToObjectFactory;

    @Override
    public void run (ApplicationArguments args) throws Exception {

        int missingEntriesCounter = 0;

        log.info("Checking if PlayerClasses are present");

        for (PlayerClassEnum playerClass : PlayerClassEnum.values()) {
            Optional<PlayerClass> playerClassFromDatabase = playerClassRepository.findById(playerClass.getId());
            if (playerClassFromDatabase.isEmpty()) {
                missingEntriesCounter++;
                playerClassRepository.save(enumToObjectFactory.createPlayerClass(playerClass));
                log.debug("Missing PlayerClass {}/{} was created", playerClass.getName(), playerClass.getId());
            }
        }
        if (missingEntriesCounter > 0) {
            log.info("PlayerClass check finished. Created {} missing entries", missingEntriesCounter);
        } else {
            log.info("PlayerClass check finished. No Missing Entries found");
        }

    }
}
