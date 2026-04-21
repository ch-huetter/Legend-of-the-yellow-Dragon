package de.game.apiController;

import de.game.controller.dto.LoginDto;
import de.game.service.factory.LoginDtoFactory;
import de.game.service.filler.dto.DtoFillerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/game")
public class LoginApiController {

    private final DtoFillerService dtoFillerService;
    private final LoginDtoFactory loginDtoFactory;

    @GetMapping("/loginInit")
    public ResponseEntity<LoginDto> initLogin () {
        LoginDto loginDto = loginDtoFactory.createLoginDto();
        dtoFillerService.fillDto(loginDto);
        return new ResponseEntity<>(loginDto, HttpStatus.OK);
    }

    @GetMapping("/csrf")
    public CsrfToken csrfToken (CsrfToken csrfToken) {
        return csrfToken;
    }

}
