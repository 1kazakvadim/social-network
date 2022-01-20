package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.CommentDao;
import by.sam_solutions.kazak.social_network.entities.Comment;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentDaoImpl extends AbstractBaseDao<Comment> implements CommentDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void saveOrUpdate(Comment obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public Comment getById(Long id) {
    return getById(Comment.class, id);
  }

  @Override
  public List<Comment> getAll() {
    return getAll(Comment.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(Comment.class, id));
  }

  @Override
  public List<Comment> getAllByPhotoId(Long id) {
    return sessionFactory.getCurrentSession()
        .createQuery("FROM Comment comment WHERE comment.photo.id = :id")
        .setParameter("id", id)
        .list();
  }

}
