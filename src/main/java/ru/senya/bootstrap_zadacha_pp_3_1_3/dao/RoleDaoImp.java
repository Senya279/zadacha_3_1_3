package ru.senya.bootstrap_zadacha_pp_3_1_3.dao;

import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.senya.bootstrap_zadacha_pp_3_1_3.model.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveRole(Role role){
        entityManager.persist(role);
    }

    @Override
    public List <Role> getAllRoles() {
        TypedQuery<Role> query = entityManager.createQuery("from Role ", Role.class);
        return List.copyOf(query.getResultList());
    }

    @Override
    public Role findRoleById(Long id){
        Role role = entityManager.find(Role.class,id);
        return role;
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
