package de.browsergame.controller;

import de.browsergame.exception.LoginNameTakenException;
import de.browsergame.factory.UserFactory;
import de.browsergame.initializer.AccountInitializer;
import de.browsergame.model.entity.User;
import de.browsergame.model.entity.dto.RegistrationDTO;
import de.browsergame.service.CreateUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final AccountInitializer accountInitializer;
    private final CreateUserService createUserService;
    private final UserFactory userFactory;
    private final PasswordEncoder passwordEncoder;

    @ModelAttribute
    private void prepareModel (RegistrationDTO registrationDTO) {
        registrationDTO.setUser(userFactory.createDefaultUser());

    }

    @GetMapping("")
    public String showRegistration (@ModelAttribute RegistrationDTO registrationDTO) {
        log.info("Registration Controller called. Show Registration executed");

        return "registration/register";
    }

    @PostMapping("/createUser")
    public String createUser (RegistrationDTO registrationDTO) {
        User newUser = registrationDTO.getUser();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        accountInitializer.initializePlayerAccount(newUser);
        log.info("Initialized User = {}", registrationDTO.getUser().toString());
        try {
            createUserService.createUserWithRoles(newUser);
        } catch (LoginNameTakenException e) {
            log.error("SShhhhtttt");
        }

        return "redirect:/login";
    }

}
