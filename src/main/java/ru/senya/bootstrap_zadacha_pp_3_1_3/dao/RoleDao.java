package ru.senya.bootstrap_zadacha_pp_3_1_3.dao;

import ru.senya.bootstrap_zadacha_pp_3_1_3.model.Role;

import java.util.List;

public interface RoleDao {

    void saveRole(Role role);

    Role findRoleById(Long id);

    List<Role> getAllRoles();

    Role findByNameRole(String name);
}
