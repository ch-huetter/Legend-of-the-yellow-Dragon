package de.browsergame.service;

import de.browsergame.exception.LoginNameTakenException;
import de.browsergame.model.entity.User;
import de.browsergame.model.repository.UserRepository;
import de.browsergame.model.repository.joinTableRepository.UserRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public void createUserWithRoles (User newUser) throws LoginNameTakenException {
        createUser(newUser);
        userRoleRepository.saveAll(newUser.getUserRoles());
    }

    @Transactional
    public void createUser (User newUser) throws LoginNameTakenException {
        if (userRepository.existByLoginName(newUser.getLoginName())) {
            throw new LoginNameTakenException("Username " + newUser.getLoginName() + " is already taken");
        }
        userRepository.save(newUser);
        log.info("saved User : {}", newUser);
    }

    /**
     * Checks if a given User is existing. If the User does not exist it will be created. If the user exists it will be checked if it is equal to the given User
     */
    public void checkUserIntegrity (User newUser) {
        Optional<User> userFromDatabase = userRepository.findByloginName(newUser.getLoginName());

        if (userFromDatabase.isPresent()) {
            if (userFromDatabase.get().equals(newUser)) {
                log.debug("User found but changes detected. Resetting default Values for {}", newUser.getLoginName());
                newUser.setId(userFromDatabase.get().getId());
                userRepository.save(userRepository.save(newUser));
            }
        } else {
            log.debug("User not found. Creating User {}", newUser.getLoginName());
            userRepository.save(newUser);
        }
        log.debug("User found and unchanged. Doing nothing for user {}", newUser.getLoginName());
    }


}
