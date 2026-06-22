package ru.senya.boot_security_pp_3_1_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.senya.boot_security_pp_3_1_2.service.UserService;

import java.security.Principal;

@Controller
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("user", userService.findByUsername(username));
        return "user";

    }
}
