package de.game.apiController.Sites.login;

import de.game.apiController.AbstractApiDtoFactory;
import de.game.model.MessageResolvableImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LoginApiDtoFactory extends AbstractApiDtoFactory<LoginApiDto> {

    public LoginApiDto createLoginDto () {
        LoginApiDto loginApiDto = new LoginApiDto();
        this.addMessages(loginApiDto);
        return loginApiDto;
    }

    @Override
    protected void addMessages (LoginApiDto dto) {
        ArrayList<MessageResolvableImpl> messages = new ArrayList<>();
        messages.add(new MessageResolvableImpl("label.user.loginName"));
        messages.add(new MessageResolvableImpl("label.user.password"));
        messages.add(new MessageResolvableImpl("error.login.wrongLoginCredentials"));
        dto.setMessageResolvableList(messages);
    }

    @Override
    protected Class<LoginApiDto> getDtoClass () {
        return LoginApiDto.class;
    }

}
