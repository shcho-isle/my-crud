package ua.javarush.dao;

import java.util.List;

import ua.javarush.model.User;

public interface UserDao {

    User findById(int id);

    void saveUser(User user);

    void deleteUserByName(String name);

    List<User> findAllUsers();

    User findUserByName(String name);

    List<User> findUsersByName(String userName);
}
