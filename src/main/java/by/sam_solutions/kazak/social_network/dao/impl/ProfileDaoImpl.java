package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.ProfileDao;
import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class ProfileDaoImpl extends AbstractBaseDao<Profile> implements ProfileDao {

  private final SessionFactory sessionFactory;

  public ProfileDaoImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void saveOrUpdate(Profile obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public Profile getById(Long id) {
    return getById(Profile.class, id);
  }

  @Override
  public List<Profile> getAll() {
    return getAll(Profile.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(Profile.class, id));
  }

  @Override
  public Profile getProfileByEmail(String email) {
    return (Profile) sessionFactory.getCurrentSession()
        .createQuery("FROM Profile WHERE user.email = :email")
        .setParameter("email", email)
        .uniqueResult();
  }

}
