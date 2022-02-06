package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.DialogDao;
import by.sam_solutions.kazak.social_network.entities.Dialog;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DialogDaoImpl extends AbstractBaseDao<Dialog> implements DialogDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void saveOrUpdate(Dialog obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public Dialog getById(Long id) {
    return getById(Dialog.class, id);
  }

  @Override
  public List<Dialog> getAll() {
    return getAll(Dialog.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(Dialog.class, id));
  }

  @Override
  public List<Dialog> getAllByProfileId(Long id) {
    return sessionFactory.getCurrentSession()
        .createQuery(
            "FROM Dialog dilog WHERE dilog.senderProfile.id = :id OR dilog.recipientProfile.id = :id")
        .setParameter("id", id)
        .list();
  }

  @Override
  public Dialog getBySenderProfileIdAndRecipientProfileId(Long senderProfileId,
      Long recipientProfileId) {
    return (Dialog) sessionFactory.getCurrentSession()
        .createQuery("FROM Dialog  dialog WHERE (dialog.senderProfile.id = :senderProfileId AND dialog.recipientProfile.id = :recipientProfileId)"
            + " OR (dialog.senderProfile.id = :recipientProfileId AND dialog.recipientProfile.id = :senderProfileId)")
        .setParameter("senderProfileId", senderProfileId)
        .setParameter("recipientProfileId", recipientProfileId).uniqueResult();
  }

}
