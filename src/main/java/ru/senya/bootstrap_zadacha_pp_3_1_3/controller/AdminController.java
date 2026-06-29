package ru.senya.bootstrap_zadacha_pp_3_1_3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.senya.bootstrap_zadacha_pp_3_1_3.model.User;
import ru.senya.bootstrap_zadacha_pp_3_1_3.service.RoleService;
import ru.senya.bootstrap_zadacha_pp_3_1_3.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String listUserMetod(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("newUser",new User());
        return "index";
    }

    @GetMapping(value = "/edit")
    public String idUserMetod(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.findUserByID(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @GetMapping(value = "/new")
    public String saveUserMetod(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping(value = "/save")
    public String saveUserPos(@ModelAttribute("user") User user,
                              @RequestParam(value = "rolesId") List<Long> rolesId) {
        userService.saveUser(user,rolesId);
        return "redirect:/admin";
    }

    @PostMapping(value = "/update")
    public String updateUserPos(@ModelAttribute("user") User user,
                                @RequestParam(value = "rolesId") List<Long> rolesId) {
        userService.updateUser(user,rolesId);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete")
    public String deleteUserPos(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

