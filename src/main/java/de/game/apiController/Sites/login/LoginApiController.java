package de.game.apiController.Sites.login;

import de.game.apiController.Sites.globalContext.GlobalContextApiDto;
import de.game.apiController.Sites.globalContext.GlobalContextApiDtoFactory;
import de.game.service.filler.dto.DtoFillerService;
import lombok.RequiredArgsConstructor;
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
    private final LoginApiDtoFactory loginApiDtoFactory;

    @GetMapping("/loginInit")
    public ResponseEntity<LoginApiDto> initLogin () {
        LoginApiDto         loginApiDto         = loginApiDtoFactory.createLoginDto();
        dtoFillerService.fillDto(loginApiDto);
        return ResponseEntity.ok(loginApiDto);
    }

    @GetMapping("/csrf")
    public CsrfToken csrfToken (CsrfToken csrfToken) {
        return csrfToken;
    }


}
