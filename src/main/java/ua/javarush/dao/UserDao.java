package ua.javarush.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ua.javarush.model.User;

@Repository
public class UserDao extends AbstractDao<Integer, User> {

    public User findById(int id) {
        return getByKey(id);
    }

    public void saveUser(User user) {
        persist(user);
    }

    public void deleteUserByName(String name) {
        Query query = getSession().createSQLQuery("delete from USER where name = :name");
        query.setString("name", name);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }

    public User findUserByName(String name) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("name", name));
        return (User) criteria.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> findUsersByName(String userName) {
        String query = "SELECT t.* FROM User t WHERE t.name like '%" + userName + "%'";
        List<Object[]> userObjects = fetchAll(query);
        List<User> users = new ArrayList<>();
        for (Object[] userObject : userObjects) {
            User user = new User();
            int id = (int) userObject[0];
            String name = (String) userObject[1];
            int age = (int) userObject[2];
            boolean admin = (boolean) userObject[3];
            Timestamp localDate = (Timestamp) userObject[4];
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            user.setAdmin(admin);
            user.setCreatedDate(localDate);
            users.add(user);
        }
        return users;
    }
}
