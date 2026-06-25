package ru.senya.bootstrap_zadacha_pp_3_1_3.dao;

import org.springframework.stereotype.Repository;
import ru.senya.bootstrap_zadacha_pp_3_1_3.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

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
