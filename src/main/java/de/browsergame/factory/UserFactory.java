package de.browsergame.factory;

import de.browsergame.enums.RoleEnum;
import de.browsergame.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final PasswordEncoder passwordEncoder;

    public User createDefaultUser(String username, String password){
        User account = new User();
        account.setLoginName(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setActive(true);
        return account;
    }


}
