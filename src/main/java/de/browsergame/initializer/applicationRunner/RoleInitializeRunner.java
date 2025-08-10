package de.browsergame.initializer.applicationRunner;

import de.browsergame.enums.RoleEnum;
import de.browsergame.factory.RoleFactory;
import de.browsergame.model.entity.Role;
import de.browsergame.model.repository.RoleRepository;
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
public class RoleInitializeRunner implements ApplicationRunner {

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
