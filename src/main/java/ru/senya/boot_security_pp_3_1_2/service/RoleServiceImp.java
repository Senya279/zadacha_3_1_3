package ru.senya.boot_security_pp_3_1_2.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senya.boot_security_pp_3_1_2.dao.RoleDao;
import ru.senya.boot_security_pp_3_1_2.dao.RoleDaoImp;
import ru.senya.boot_security_pp_3_1_2.model.Role;

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
    public Role findByNameRole(String name){
        return roleDao.findByNameRole(name);
    }
}
