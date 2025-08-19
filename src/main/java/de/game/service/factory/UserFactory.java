package de.game.service.factory;

import de.game.model.entity.User;
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
        user.setMaxCharacters(3);
    }

}
