package de.game.apiController.sites.login;

import de.game.service.UserService;
import de.game.service.filler.dto.DtoFiller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginApiDtoFiller implements DtoFiller<LoginApiDto> {

    final UserService userService;

    @Override
    public void fillDto (LoginApiDto dto) {
        dto.setIsLoggedIn(userService.isLoggedIn());
    }

    @Override
    public Class<LoginApiDto> supports () {
        return LoginApiDto.class;
    }
}
