package by.sam_solutions.kazak.social_network.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseDao<T> {

  private T entity;

  @Autowired
  private SessionFactory sessionFactory;

  protected void saveOrUpdate(T obj) {
    sessionFactory.getCurrentSession().saveOrUpdate(obj);
  }

  protected T getById(Class<T> aClass, Integer id) {
    return sessionFactory.getCurrentSession().get(aClass, id);
  }

  protected List<T> getAll(Class<T> aClass) {
    return sessionFactory.getCurrentSession().createQuery("FROM " + aClass.getName()).list();
  }

  protected void delete(T obj) {
    sessionFactory.getCurrentSession().delete(obj);
  }

}
