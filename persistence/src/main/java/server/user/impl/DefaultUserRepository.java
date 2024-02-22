package server.user.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import server.User;
import server.user.UserRepository;

@Component
public class DefaultUserRepository implements UserRepository {
    private SessionFactory sessionFactory;
    public DefaultUserRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public User getOne(String s) {
        User user = null;
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                user = session.get(User.class, s);
                transaction.commit();
            }catch(RuntimeException ex){
                if(transaction != null)
                    transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public Iterable<User> getAll() {
        return null;
    }

    @Override
    public User add(User user) {
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = null;
            try{
                transaction = session.beginTransaction();
                session.save(user);
                transaction.commit();
            }catch (RuntimeException ex){
                if(transaction != null)
                    transaction.rollback();
            }
        }
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
