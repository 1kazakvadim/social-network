package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.ProfileDao;
import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;
import org.apache.lucene.search.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfileDaoImpl extends AbstractBaseDao<Profile> implements ProfileDao {

  @Autowired
  private SessionFactory sessionFactory;

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
  public List<Profile> getAll(Integer page, Integer size) {
    org.hibernate.query.Query query = sessionFactory.getCurrentSession()
        .createQuery("FROM Profile profile");
    query.setFirstResult(page * size);
    query.setMaxResults(size);
    return (List<Profile>) query.list();
  }

  @Override
  public Profile getProfileByEmail(String email) {
    return (Profile) sessionFactory.getCurrentSession()
        .createQuery("FROM Profile WHERE user.email = :email")
        .setParameter("email", email)
        .uniqueResult();
  }

  @Override
  public Profile getProfileByUserId(Long id) {
    return (Profile) sessionFactory.getCurrentSession()
        .createQuery("FROM Profile WHERE user.id = :id")
        .setParameter("id", id)
        .uniqueResult();
  }

  @Override
  public List<Profile> searchForProfiles(String search) throws Exception {
    FullTextEntityManager fullTextEntityManager = Search.getFullTextSession(
        sessionFactory.getCurrentSession());
    fullTextEntityManager.createIndexer().startAndWait();
    FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
    QueryBuilder queryBuilder = fullTextSession
        .getSearchFactory()
        .buildQueryBuilder()
        .forEntity(Profile.class)
        .get();
    Query query = queryBuilder
        .keyword()
        .onField("basicInformation.fullName")
        .matching(search)
        .createQuery();
    List<Profile> results = fullTextSession.createFullTextQuery(query, Profile.class)
        .getResultList();
    return results;
  }

  @Override
  public Long countProfiles() {
    return (Long) sessionFactory.getCurrentSession()
        .createQuery("SELECT count(profile) FROM Profile profile")
        .getSingleResult();
  }

}
