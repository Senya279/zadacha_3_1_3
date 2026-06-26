package ru.senya.bootstrap_zadacha_pp_3_1_3.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senya.bootstrap_zadacha_pp_3_1_3.dao.RoleDao;
import ru.senya.bootstrap_zadacha_pp_3_1_3.model.Role;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    @Transactional
    @Override
    public void saveRole(Role role){
        roleDao.saveRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles(){
        return roleDao.getAllRoles();
    }

    @Transactional(readOnly = true)
    @Override
    public Role findRoleById(Long id){
        return roleDao.findRoleById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Role findByNameRole(String name){
        return roleDao.findByNameRole(name);
    }
}
