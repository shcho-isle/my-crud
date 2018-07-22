package ua.javarush.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.javarush.model.User;

import java.util.List;

@Repository
@Transactional
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public User get(Long id) {
        return getSession().get(User.class, id);
    }

    public void update(User user) {
        getSession().update(user);
    }

    public void save(User user) {
        getSession().persist(user);
    }

    public void delete(Long id) {
        Query query = getSession().createQuery("delete from User where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public List<User> getAll() {
        Query<User> query = getSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    public List<User> findByName(String userName) {
        Query<User> query = getSession().createQuery("from User where name like :name", User.class);
        query.setParameter("name", "%" + userName + "%");
        return query.getResultList();
    }
}
