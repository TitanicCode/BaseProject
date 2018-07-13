package api.dao.impl;

import api.dao.UserDao;
import api.pojo.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public List<User> queryUserList() {
        Query<User> query = getCurrentSession().createQuery("from User", User.class);
        return query.list();
    }
}
