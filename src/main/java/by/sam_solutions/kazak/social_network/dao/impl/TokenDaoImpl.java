package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.TokenDao;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.entities.Token;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenDaoImpl extends AbstractBaseDao<Token> implements
    TokenDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void saveOrUpdate(Token obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public Token getByToken(String token) {
    return (Token)
        sessionFactory
            .getCurrentSession()
            .createQuery("FROM Token WHERE token = :token")
            .setParameter("token", token)
            .uniqueResult();
  }

  @Override
  public User getUserByToken(String token) {
    return (User)
        sessionFactory
            .getCurrentSession()
            .createQuery("SELECT user FROM Token WHERE token = :token")
            .setParameter("token", token)
            .uniqueResult();
  }

  @Override
  public Token getById(Long id) {
    return getById(Token.class, id);
  }

  @Override
  public List<Token> getAll() {
    return getAll(Token.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(Token.class, id));
  }

}
