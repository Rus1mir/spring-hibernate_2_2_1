package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;
    private final String USER_BY_CAR_HQL = "from User user where user.car.model = :Mod and user.car.series = :Ser";

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String model, String series) {
        TypedQuery<User> query = sessionFactory.openSession().createQuery(USER_BY_CAR_HQL);
        query.setParameter("Mod", model);
        query.setParameter("Ser", series);
        User user = query.getSingleResult();
        return user;
    }

}
