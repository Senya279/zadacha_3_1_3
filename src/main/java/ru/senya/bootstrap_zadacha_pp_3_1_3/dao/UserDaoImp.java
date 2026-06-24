package ru.senya.bootstrap_zadacha_pp_3_1_3.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import ru.senya.bootstrap_zadacha_pp_3_1_3.model.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User ", User.class);
        return List.copyOf(query.getResultList());
    }

    @Override
    public User findUserByID(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        try {
            return entityManager.createQuery(
                            "select u from User u " +
                                    "left join fetch u.roles " +
                                    "where u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // обрабатываю исключение если не найден по логину юзер нужно добавить в сервисе так же обработку этого null!!
        }
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);

    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
