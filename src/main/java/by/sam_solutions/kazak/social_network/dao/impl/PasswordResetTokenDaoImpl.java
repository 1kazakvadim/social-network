package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.PasswordResetTokenDao;
import by.sam_solutions.kazak.social_network.entities.PasswordResetToken;
import by.sam_solutions.kazak.social_network.entities.User;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class PasswordResetTokenDaoImpl extends AbstractBaseDao<PasswordResetToken> implements
    PasswordResetTokenDao {

  private final SessionFactory sessionFactory;

  public PasswordResetTokenDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void saveOrUpdate(PasswordResetToken obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public PasswordResetToken getByToken(String token) {
    return (PasswordResetToken)
        sessionFactory
            .getCurrentSession()
            .createQuery("FROM PasswordResetToken WHERE token = :token")
            .setParameter("token", token)
            .uniqueResult();
  }

  @Override
  public User getUserByToken(String token) {
    return (User)
        sessionFactory
            .getCurrentSession()
            .createQuery("SELECT user FROM PasswordResetToken WHERE token = :token")
            .setParameter("token", token)
            .uniqueResult();
  }

  @Override
  public PasswordResetToken getById(Long id) {
    return getById(PasswordResetToken.class, id);
  }

  @Override
  public List<PasswordResetToken> getAll() {
    return getAll(PasswordResetToken.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(PasswordResetToken.class, id));
  }

}
