package de.game.service.initializer.applicationRunner;

import de.game.model.entity.Role;
import de.game.model.enums.RoleEnum;
import de.game.model.repository.RoleRepository;
import de.game.service.factory.RoleFactory;
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
public class RoleIntegrityCheck implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final RoleFactory roleFactory;

    @Override
    public void run (ApplicationArguments args) throws Exception {
        log.info("Checking if Default Roles are present");
        for (RoleEnum role : RoleEnum.values()) {
            Optional<Role> roleFromDatabase = roleRepository.findByName(role.getName());
            Role           roleTemplate     = roleFactory.getRoleForEnumEntry(role);

            if (roleFromDatabase.isEmpty() || !roleTemplate.equals(roleFromDatabase.get())) {
                log.info("Default Roles not found or they have changed. Creating/Updating them");
                roleRepository.save(roleTemplate);
            }
        }
        log.info("Default role check finished");
    }


}
