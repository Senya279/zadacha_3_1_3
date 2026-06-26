package ru.senya.bootstrap_zadacha_pp_3_1_3.configs;

import org.springframework.stereotype.Component;
import ru.senya.bootstrap_zadacha_pp_3_1_3.model.Role;
import ru.senya.bootstrap_zadacha_pp_3_1_3.model.User;
import ru.senya.bootstrap_zadacha_pp_3_1_3.service.RoleService;
import ru.senya.bootstrap_zadacha_pp_3_1_3.service.UserService;

import jakarta.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataInitializer {

    private final RoleService roleService;
    private final UserService userService;

    public DataInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct

    public void init() {

        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role(("ROLE_USER"));
        roleService.saveRole(adminRole);
        roleService.saveRole(userRole);

        User admin = new User();
        admin.setName("Bob");
        admin.setSurname("Bobos");
        admin.setAge(32);
        admin.setEmail("admin@mail.ru");
        admin.setPassword("admin");
        admin.setRoles(Set.of(adminRole,userRole));
        userService.saveUser(admin,null);


        User users = new User();
        users.setName("Goga");
        users.setSurname("Gogach");
        users.setAge(19);
        users.setEmail("user@mail.ru");
        users.setPassword("user");
        users.setRoles(Set.of(userRole));
        userService.saveUser(users,null);
    }
}
