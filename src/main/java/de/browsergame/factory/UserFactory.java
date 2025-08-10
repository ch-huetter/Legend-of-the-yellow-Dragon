package de.browsergame.factory;

import de.browsergame.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    public User createDefaultUser () {
        User account = new User();
        addDefaultValues(account);
        return account;
    }

    public User createDefaultUser (String username, String password) {
        User account = new User();
        account.setLoginName(username);
        account.setPassword(passwordEncoder.encode(password));
        addDefaultValues(account);
        return account;
    }

    private void addDefaultValues (User user) {
        user.setActive(true);
        user.setUserRoles(new HashSet<>());
    }

}
