package ru.senya.bootstrap_zadacha_pp_3_1_3.dao;

import ru.senya.bootstrap_zadacha_pp_3_1_3.model.Role;

public interface RoleDao {

    void saveRole(Role role);

    Role findByNameRole(String name);
}
