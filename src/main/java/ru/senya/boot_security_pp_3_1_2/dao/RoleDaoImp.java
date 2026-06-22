package ru.senya.boot_security_pp_3_1_2.dao;

import org.springframework.stereotype.Repository;
import ru.senya.boot_security_pp_3_1_2.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveRole(Role role){
        entityManager.persist(role);
    }

    @Override
    public Role findByNameRole(String name){
        try {
            return entityManager.createQuery(
                            " from Role u where u.name = :name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}
