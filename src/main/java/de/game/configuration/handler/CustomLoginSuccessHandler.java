package de.game.configuration.handler;

import de.game.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess (HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
        User user = (User) auth.getPrincipal();

        log.info("Nutzer {} eingeloggt. Lade Nutzerdaten.", auth.getName());
        log.info("Nutzer mit Daten {} gefunden", auth.getDetails().toString());

        log.info("Nutzer mit dem Principal {}", auth.getPrincipal().toString());
        
        response.sendRedirect("/home");
    }
}
