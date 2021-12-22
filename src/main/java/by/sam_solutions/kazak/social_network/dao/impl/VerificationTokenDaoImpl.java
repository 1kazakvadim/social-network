package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.VerificationTokenDao;
import by.sam_solutions.kazak.social_network.entities.VerificationToken;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class VerificationTokenDaoImpl extends AbstractBaseDao<VerificationToken> implements
    VerificationTokenDao {

  private final SessionFactory sessionFactory;

  public VerificationTokenDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void saveOrUpdate(VerificationToken obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public VerificationToken findByToken(String token) {
    return (VerificationToken)
        sessionFactory
            .getCurrentSession()
            .createQuery("FROM VerificationToken WHERE token = :token")
            .setParameter("token", token)
            .uniqueResult();
  }

  @Override
  public VerificationToken getById(Long id) {
    return getById(VerificationToken.class, id);
  }

  @Override
  public List<VerificationToken> getAll() {
    return getAll(VerificationToken.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(VerificationToken.class, id));
  }

}