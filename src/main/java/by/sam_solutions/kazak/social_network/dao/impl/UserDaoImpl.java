package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.UserDao;
import by.sam_solutions.kazak.social_network.entities.User;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl extends AbstractBaseDao<User> implements UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void saveOrUpdate(User obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public User getById(Long id) {
    return getById(User.class, id);
  }

  @Override
  public List<User> getAll() {
    return getAll(User.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(User.class, id));
  }

  @Override
  public User findByEmail(String email) {
    return (User)
        sessionFactory
            .getCurrentSession()
            .createQuery("FROM User WHERE email = :email")
            .setParameter("email", email)
            .uniqueResult();
  }

  @Override
  public User getByToken(String token) {
    return (User)
        sessionFactory
            .getCurrentSession()
            .createQuery("SELECT user FROM Token WHERE token = :token")
            .setParameter("token", token)
            .uniqueResult();
  }

  @Override
  public boolean isEmailExists(String email) {
    return sessionFactory
        .getCurrentSession().createQuery(
            "SELECT 1 FROM User WHERE EXISTS (SELECT 1 FROM User p WHERE email = :email)")
        .setParameter("email", email)
        .uniqueResult() != null;
  }

}
