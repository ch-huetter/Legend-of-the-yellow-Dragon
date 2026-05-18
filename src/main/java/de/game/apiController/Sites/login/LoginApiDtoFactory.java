package de.game.apiController.Sites.login;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LoginApiDtoFactory extends AbstractApiDtoFactory<LoginApiDto> {

    public LoginApiDto createLoginDto () {
        LoginApiDto loginApiDto = new LoginApiDto();
        this.addMessages(loginApiDto);
        loginApiDto.setBackgroundUrl("/images/background/login_background.png");
        return loginApiDto;
    }

    @Override
    protected void addMessages (LoginApiDto dto) {
        ArrayList<MessageResolvable> messages = new ArrayList<>();
        messages.add(new MessageResolvable("label.user.loginName"));
        messages.add(new MessageResolvable("label.user.password"));
        messages.add(new MessageResolvable("error.login.wrongLoginCredentials"));
        dto.setMessageResolvableList(messages);
    }

    @Override
    protected Class<LoginApiDto> getDtoClass () {
        return LoginApiDto.class;
    }

}
