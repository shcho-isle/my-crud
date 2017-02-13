package ua.javarush.service;

import java.util.List;

import ua.javarush.model.User;

public interface UserService {

    User findById(int id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserByName(String name);

    List<User> findAllUsers();

    User findUserByName(String name);

    boolean isUserNameUnique(Integer id, String name);

    List<User> findUsersByName(String userName);
}
