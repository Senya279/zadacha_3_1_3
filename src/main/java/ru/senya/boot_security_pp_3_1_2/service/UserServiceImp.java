package ru.senya.boot_security_pp_3_1_2.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senya.boot_security_pp_3_1_2.dao.UserDao;
import ru.senya.boot_security_pp_3_1_2.model.User;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;

    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserByID(Long id) {
        return userDao.findUserByID(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден: " + username);
        }
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User findByUsername (String username){
        return userDao.findByUsername(username);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}

