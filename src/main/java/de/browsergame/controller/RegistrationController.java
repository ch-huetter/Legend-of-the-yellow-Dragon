package de.browsergame.controller;

import de.browsergame.model.entity.dto.RegistrationDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/register")
    public String showRegistration(@ModelAttribute RegistrationDTO registrationDTO, HttpServletRequest request){

        return "register";
    }

    @PostMapping("/register/createUser")
    public void createUser(@ModelAttribute RegistrationDTO registrationDTO, Model model, HttpServletRequest request){
        log.info("Recieved data = {}", registrationDTO.getUser().toString());
    }

}
