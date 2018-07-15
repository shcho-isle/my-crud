package ua.javarush.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import ua.javarush.model.User;

@Repository
@Transactional
public class UserDao extends AbstractDao<Integer, User> {

    public User get(Integer id) {
        return getByKey(id);
    }

    public void save(User user) {
        persist(user);
    }

    public void update(User user) {
        User entity = get(user.getId());
        if (entity != null) {
            entity.setName(user.getName());
            entity.setAge(user.getAge());
            entity.setAdmin(user.isAdmin());
        }
    }

    public void delete(Integer id) {
        Query query = getSession().createSQLQuery("delete from USER where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        Criteria criteria = createEntityCriteria();
        return (List<User>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    public List<User> findByName(String userName) {
        String query = "SELECT t.* FROM User t WHERE t.name like '%" + userName + "%'";
        List<Object[]> userObjects = fetchAll(query);
        List<User> users = new ArrayList<>();
        for (Object[] userObject : userObjects) {
            User user = new User();
            Integer id = (Integer) userObject[0];
            String name = (String) userObject[1];
            Integer age = (Integer) userObject[2];
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
