package ru.senya.boot_security_pp_3_1_2.dao;

import ru.senya.boot_security_pp_3_1_2.model.Role;

public interface RoleDao {

    void saveRole(Role role);

    Role findByNameRole(String name);
}
