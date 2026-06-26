package ru.senya.bootstrap_zadacha_pp_3_1_3.service;

import ru.senya.bootstrap_zadacha_pp_3_1_3.model.Role;

import java.util.List;

public interface RoleService {

    void saveRole(Role role);

    Role findRoleById(Long id);

    List <Role> getAllRoles();

    Role findByNameRole(String name);
}
