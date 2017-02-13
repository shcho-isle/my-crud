package ua.javarush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.javarush.dao.UserDao;
import ua.javarush.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao dao;

    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    public User findById(int id) {
        return dao.findById(id);
    }

    public void saveUser(User user) {
        dao.saveUser(user);
    }

    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if (entity != null) {
            entity.setName(user.getName());
            entity.setAge(user.getAge());
            entity.setAdmin(user.isAdmin());
        }
    }

    public void deleteUserByName(String name) {
        dao.deleteUserByName(name);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public User findUserByName(String name) {
        return dao.findUserByName(name);
    }

    public boolean isUserNameUnique(Integer id, String name) {
        User user = findUserByName(name);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

    public List<User> findUsersByName(String userName) {
        return dao.findUsersByName(userName);
    }

}
