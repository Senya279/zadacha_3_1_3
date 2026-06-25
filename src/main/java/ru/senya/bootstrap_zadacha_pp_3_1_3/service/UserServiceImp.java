package ru.senya.bootstrap_zadacha_pp_3_1_3.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.senya.bootstrap_zadacha_pp_3_1_3.dao.UserDao;
import ru.senya.bootstrap_zadacha_pp_3_1_3.model.Role;
import ru.senya.bootstrap_zadacha_pp_3_1_3.model.User;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserDao userDao;
    private final RoleService roleService;

    public UserServiceImp(PasswordEncoder passwordEncoder, UserDao userDao, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.roleService = roleService;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден: " + email);
        }
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public User findByEmail (String email){
        return userDao.findByEmail(email);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role userRole = roleService.findByNameRole("ROLE_USER");
            user.setRoles(Set.of(userRole));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        User existing = userDao.findUserByID(user.getId());
        user.setRoles(existing.getRoles());
        if (user.getPassword().isEmpty()
                || user.getPassword().equals(existing.getPassword())) {
            user.setPassword(existing.getPassword());

        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}

