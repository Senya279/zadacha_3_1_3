package ru.senya.boot_security_pp_3_1_2.dao;

import ru.senya.boot_security_pp_3_1_2.model.User;

import java.util.List;

public interface UserDao {
    List<User> listUsers();

    User findUserByID(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    User findByUsername (String username);
}
