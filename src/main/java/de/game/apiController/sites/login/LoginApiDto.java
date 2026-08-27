package de.game.apiController.sites.login;

import de.game.apiController.AbstractApiDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LoginApiDto extends AbstractApiDto {
    private Boolean isLoggedIn;
}
