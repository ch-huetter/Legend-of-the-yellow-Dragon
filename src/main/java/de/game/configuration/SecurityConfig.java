package de.game.configuration;

import de.game.model.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain webSecurity (HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                     )
                .authorizeHttpRequests(auth -> auth.requestMatchers("/backoffice/login", "/registration/**", "/css/**", "/webjars/**", "/images/**", "/js/**", "/api/auth/login",
                                                                    "/api/auth/logout", "/error", "/api/game/loginInit", "/api/game/csrf",
                                                                    "/api/game/globalContext/checkGlobalContext").permitAll()
                        .requestMatchers("/backoffice/**").hasAnyRole(RoleEnum.ADMIN.getName(), RoleEnum.GAMEMASTER.getName()).anyRequest().authenticated())

                .formLogin(form -> form
                                   .loginPage("/backoffice/login")
                                   .loginProcessingUrl("/backoffice/login")
                                   .usernameParameter("username")
                                   .passwordParameter("password")
                                   .failureUrl("/login?error")
                                   .successHandler(backofficeSuccessHandler())
                          )
                .exceptionHandling(ex -> ex
                                           .defaultAuthenticationEntryPointFor(
                                                   (req, res, e) -> res.sendError(401),
                                                   req -> req.getRequestURI().startsWith("/api/")
                                                                              )
                                           .defaultAuthenticationEntryPointFor(
                                                   (req, res, e) -> res.sendError(403),
                                                   req -> req.getRequestURI().startsWith("/api/")
                                                                              )
                                  )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                       );

        return http.build();
    }


    @Bean
    public AuthenticationSuccessHandler backofficeSuccessHandler () {
        return (request, response, authentication) -> {
            boolean allowed = authentication.getAuthorities().stream()
                    .anyMatch(a ->
                                      a.getAuthority().equals(RoleEnum.ADMIN.getName()) ||
                                      a.getAuthority().equals(RoleEnum.GAMEMASTER.getName())
                             );

            if (!allowed) {
                request.getSession(false).invalidate();
                response.sendRedirect("/login?denied");
                return;
            }

            response.sendRedirect("/backoffice/home");
        };
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
