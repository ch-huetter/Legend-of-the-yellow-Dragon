package de.game.service;

import de.game.model.entity.User;
import de.game.model.repository.UserRepository;
import de.game.model.repository.joinTableRepository.UserRoleRepository;
import de.game.util.basic.BasicEmptyCheck;
import de.game.util.exception.LoginNameTakenException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User getLoggedInUserFromDb () {
        User userFromSecurity;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            userFromSecurity = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.debug("User in Security Context is {}", userFromSecurity);
        } else {
            return null;
        }
        return userRepository.findById(userFromSecurity.getId()).orElseThrow(NullPointerException::new);
    }

    @Transactional
    public void createUserWithRoles (User newUser) throws LoginNameTakenException {
        createUser(newUser);
        userRoleRepository.saveAll(newUser.getUserRoles());
    }

    @Transactional
    public void createUser (User newUser) throws LoginNameTakenException {
        if (userRepository.existsByloginName(newUser.getLoginName())) {
            throw new LoginNameTakenException("Username " + newUser.getLoginName() + " is already taken");
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.save(newUser);
        log.info("saved User : {}", newUser);
    }

    /**
     * Patches user-controlled profile fields.
     * ⚠️ Security note:
     * This method MUST NOT modify security-critical fields such as
     * password, email, loginName, roles, or account flags.
     * Password and identity-related changes are handled explicitly
     * by dedicated methods to avoid accidental privilege or credential changes.
     */
    @Transactional
    public void updateUser (User userToUpdate) {
        if (userToUpdate.getId() == null) {
            throw new NullPointerException("No UserId set");
        }
        User userFromDb = userRepository.findById(userToUpdate.getId()).orElseThrow(() -> new NullPointerException("No User Found for Id + " + userToUpdate.getId()));

        if (userToUpdate.getGender() != null) {
            userFromDb.setGender(userToUpdate.getGender());
        }
        if (BasicEmptyCheck.isSet(userToUpdate.getEMail())) {
            userFromDb.setEMail(userToUpdate.getEMail());
        }
        if (BasicEmptyCheck.isSet(userFromDb.getActivePlayerCharacterId())) {
            userFromDb.setActivePlayerCharacterId(userToUpdate.getActivePlayerCharacterId());
        }

        userRepository.save(userFromDb);
    }

    /**
     * Checks the Security Context if the current User is an authenticated non anonymus User
     *
     * @return True if a registered User is logged in an anonymus
     */
    public boolean isLoggedIn () {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (securityContext.getAuthentication().isAuthenticated() && !(securityContext.getAuthentication() instanceof AnonymousAuthenticationToken));
    }

    /**
     * Checks if a given User is existing. If the User does not exist it will be created. If the user exists it will be checked if it is equal to the given User
     */
    public void checkUserIntegrity (User newUser) {
        //TODO Doesnt work when User exists with wrong Values
        Optional<User> userFromDatabase = userRepository.findByloginNameWithUserRoles(newUser.getLoginName());
        if (userFromDatabase.isPresent()) {
            if (!userFromDatabase.get().equals(newUser) || !passwordEncoder.matches(newUser.getPassword(), userFromDatabase.get().getPassword())) {
                newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
                log.info("User found but changes detected. Resetting default Values for {}", newUser.getLoginName());
                newUser.setId(userFromDatabase.get().getId());
                userRepository.save(newUser);
                userRoleRepository.saveAll(newUser.getUserRoles());
            }
        } else {
            log.info("User not found. Creating User {}", newUser.getLoginName());
            try {
                createUserWithRoles(newUser);
            } catch (LoginNameTakenException e) {
                //Unhandled. User Existence gets checked earlier so this Error cannot appear
            }
        }
    }
}
