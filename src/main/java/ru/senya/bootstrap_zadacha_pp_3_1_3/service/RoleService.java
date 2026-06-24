package ru.senya.bootstrap_zadacha_pp_3_1_3.service;

import ru.senya.bootstrap_zadacha_pp_3_1_3.model.Role;

public interface RoleService {

    void saveRole(Role role);

    Role findByNameRole(String name);
}
