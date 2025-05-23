package hiber.dao;

import hiber.model.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


   private SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {

      sessionFactory.getCurrentSession().persist(user);

   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserFromCar(String model, int series) {

      TypedQuery<User> query =  sessionFactory.getCurrentSession()
              .createQuery("select u from User u join u.car c where c.model =:model AND c.series =:series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return  query.getSingleResult();

   }
}
