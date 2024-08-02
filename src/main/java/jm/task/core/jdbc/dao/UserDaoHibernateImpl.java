package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.createNativeQuery("""
        create table if not exists User (
            id BIGINT primary key auto_increment,
            name varchar(255),
            lastName varchar(255),
            age TINYINT
        )
        """).executeUpdate();
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.createNativeQuery("drop table if exists User").executeUpdate();
        session.getTransaction().commit();

        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();

        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.createQuery("delete from User where id = :id").setParameter("id", id).executeUpdate();
        session.getTransaction().commit();

        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();

        session.close();
    }
}
