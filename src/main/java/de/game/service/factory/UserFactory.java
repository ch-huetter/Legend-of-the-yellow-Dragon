package de.game.service.factory;

import de.game.model.entity.User;
import de.game.util.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class UserFactory {

    public User createDefaultUser () {
        User account = new User();
        addDefaultValues(account);
        return account;
    }

    private void addDefaultValues (User user) {
        user.setActive(true);
        user.setUserRoles(new HashSet<>());
        user.setGender(Gender.DIVERSE);
        user.setMaxCharacters(3);
    }

}
