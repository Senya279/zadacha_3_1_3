package ru.senya.boot_security_pp_3_1_2.service;

import ru.senya.boot_security_pp_3_1_2.model.Role;

public interface RoleService {

    void saveRole(Role role);

    Role findByNameRole(String name);
}
