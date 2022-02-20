package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.PhotoDao;
import by.sam_solutions.kazak.social_network.entities.Photo;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotoDaoImpl extends AbstractBaseDao<Photo> implements PhotoDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void saveOrUpdate(Photo obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public Photo getById(Long id) {
    return getById(Photo.class, id);
  }

  @Override
  public List<Photo> getAll() {
    return getAll(Photo.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(Photo.class, id));
  }

  @Override
  public List<Photo> getAllByProfileId(Long id, Integer page, Integer size) {
    Query query = sessionFactory.getCurrentSession()
        .createQuery("FROM Photo photo WHERE photo.profile.id = :id")
        .setParameter("id", id);
    query.setFirstResult(page * size);
    query.setMaxResults(size);
    return (List<Photo>) query.list();
  }

  @Override
  public List<Photo> getAllByProfileId(Long id) {
    return sessionFactory.getCurrentSession()
        .createQuery(
            "FROM Photo photo WHERE photo.profile.id = :id")
        .setParameter("id", id)
        .list();
  }

  @Override
  public Long countByProfileId(Long id) {
    return (Long) sessionFactory.getCurrentSession()
        .createQuery("SELECT count(photo) FROM Photo photo WHERE photo.profile.id = :id")
        .setParameter("id", id)
        .getSingleResult();
  }

}
