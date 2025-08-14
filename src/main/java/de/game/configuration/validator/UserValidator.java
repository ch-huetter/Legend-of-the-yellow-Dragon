package de.game.configuration.validator;

import de.game.model.entity.User;
import de.game.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public Boolean validateUser (User user) {
        boolean isValid = true;

        if (!validateLoginName(user))
            isValid = false;

        if (!validatePasswordLength(user)) {
            isValid = false;
        }

        if (validateEmailAddressExists(user)) {
            isValid = false;
        }

        return isValid;
    }

    public boolean validateLoginName (User user) {
        return userRepository.existsByloginName(user.getLoginName());
    }


    public boolean validatePasswordLength (User user) {
        String password = user.getPassword();
        return !(password.length() >= 5 && password.length() <= 64);
    }

    public boolean validateEmailAddressExists (User user) {
        return userRepository.existsByeMail(user.getEMail());
    }
}
