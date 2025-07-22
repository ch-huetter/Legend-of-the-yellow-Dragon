package de.browsergame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.http.HttpRequest;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String showLandingPage(Model model, HttpRequest request){

        return "dashboard";
    }
}
