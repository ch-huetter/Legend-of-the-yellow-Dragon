package de.game.controller;

import de.game.controller.dto.RegistrationDto;
import de.game.model.entity.User;
import de.game.service.CreateUserService;
import de.game.service.factory.UserFactory;
import de.game.service.initializer.AccountInitializer;
import de.game.util.exception.LoginNameTakenException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    private void prepareModel (RegistrationDto registrationDto) {
        registrationDto.setUser(userFactory.createDefaultUser());

    }

    @GetMapping("")
    public String showRegistration (@ModelAttribute RegistrationDto registrationDto) {
        log.info("Registration Controller called. Show Registration executed");

        return "registration/register";
    }

    @PostMapping("/createUser")
    public String createUser (@Valid RegistrationDto registrationDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("br has errors. redirecting to register page");
            return showRegistration(registrationDto);
        }
        User newUser = registrationDto.getUser();
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        accountInitializer.initializePlayerAccount(newUser);
        log.info("Initialized User = {}", registrationDto.getUser().toString());
        try {
            createUserService.createUserWithRoles(newUser);
        } catch (LoginNameTakenException e) {
            log.error("SShhhhtttt");
        }
        return "redirect:/login";
    }

}
