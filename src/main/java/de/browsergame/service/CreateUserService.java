package de.browsergame.service;

import de.browsergame.model.entity.Role;
import de.browsergame.model.entity.User;
import de.browsergame.model.entity.joinTable.UserRole;
import de.browsergame.model.repository.UserRepository;
import de.browsergame.model.repository.joinTableRepository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public void createUserWithRoles (UserWithRolesForCreation userForCreation){

        User createdUser = createUser(userForCreation.getUser());
        Set<Role>     rolesForUser = userForCreation.getRoles();
        Set<UserRole> missingRoles = new HashSet<>();

        rolesForUser.forEach(role -> {
            UserRole userRole = new UserRole(createdUser, role);
            if(!createdUser.getUserRoles().contains(userRole)) {
                missingRoles.add(userRole);
            }
        });

        if(!missingRoles.isEmpty()){
            log.debug("Missing Roles Detected. Adding {} Roles for User {}", missingRoles.size(), createdUser.getLoginName());
            userRoleRepository.saveAll(missingRoles);
        }


    }

    public User createUser(User newUser) {
        Optional<User> userFromDatabase = userRepository.findByloginName(newUser.getLoginName());

        if (userFromDatabase.isPresent()) {
            if (userFromDatabase.get().equals(newUser)) {
                log.debug("User found but changes detected. Resetting default Values for {}", newUser.getLoginName());
                newUser.setId(userFromDatabase.get().getId());
                userRepository.save(userRepository.save(newUser));
                return newUser;
            }
        } else {
            log.debug("User not found. Creating User {}", newUser.getLoginName());
            userRepository.save(newUser);
            return newUser;
        }

        log.debug("User found and unchanged. Doing nothing for user {}", newUser.getLoginName());
        return userFromDatabase.get();
    }



    @Getter
    @Setter
    @AllArgsConstructor
    public static class UserWithRolesForCreation {
        private User user;
        private Set<Role> roles;

    }


}
