package ua.javarush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.javarush.dao.UserDao;
import ua.javarush.model.User;

@Service
@Transactional
public class UserService {

    private final UserDao dao;

    @Autowired
    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public void save(User user) {
        dao.save(user);
    }

    public void update(User user) {
        User entity = dao.get(user.getId());
        if (entity != null) {
            entity.setName(user.getName());
            entity.setAge(user.getAge());
            entity.setAdmin(user.isAdmin());
        }
    }

    public void deleteByName(String name) {
        dao.deleteByName(name);
    }

    public List<User> getAll() {
        return dao.getAll();
    }

    public User getByName(String name) {
        return dao.getByName(name);
    }

    public boolean isUserNameUnique(Integer id, String name) {
        User user = getByName(name);
        return (user == null || ((id != null) && (id.equals(user.getId()))));
    }

    public List<User> findByName(String userName) {
        return dao.findByName(userName);
    }

}
