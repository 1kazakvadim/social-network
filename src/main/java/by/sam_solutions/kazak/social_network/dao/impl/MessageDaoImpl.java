package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.MessageDao;
import by.sam_solutions.kazak.social_network.entities.Message;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageDaoImpl extends AbstractBaseDao<Message> implements MessageDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void saveOrUpdate(Message obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public Message getById(Long id) {
    return getById(Message.class, id);
  }

  @Override
  public List<Message> getAll() {
    return getAll(Message.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(Message.class, id));
  }

  @Override
  public List<Message> getAllByDialogId(Long id) {
    return sessionFactory.getCurrentSession()
        .createQuery(
            "FROM Message message WHERE message.dialog.id = :id ORDER BY message.id ASC")
        .setParameter("id", id)
        .list();
  }

}
