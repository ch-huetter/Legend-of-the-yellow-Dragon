package de.game.service.factory;

import de.game.controller.dto.LoginDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class LoginDtoFactory {

    public LoginDto createLoginDto () {
        LoginDto                loginDto = new LoginDto();
        HashMap<String, String> messages = new HashMap<>();
        messages.put("label.user.loginName", "");
        messages.put("label.user.password", "");
        loginDto.setMessages(messages);
        loginDto.setBackgroundUrl("/images/background/login_background.png");
        return loginDto;
    }
}
