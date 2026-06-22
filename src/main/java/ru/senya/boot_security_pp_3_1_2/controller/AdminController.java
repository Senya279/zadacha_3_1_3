package ru.senya.boot_security_pp_3_1_2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.senya.boot_security_pp_3_1_2.model.Role;
import ru.senya.boot_security_pp_3_1_2.model.User;
import ru.senya.boot_security_pp_3_1_2.service.RoleService;
import ru.senya.boot_security_pp_3_1_2.service.UserService;

import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String listUserMetod(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "index";
    }

    @GetMapping(value = "/edit")
    public String idUserMetod(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findUserByID(id));
        return "edit";
    }

    @GetMapping(value = "/new")
    public String saveUserMetod(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping(value = "/save")
    public String saveUserPos(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/update")
    public String updateUserPos(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete")
    public String deleteUserPos(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

