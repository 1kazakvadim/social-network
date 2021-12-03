package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.UserDao;
import by.sam_solutions.kazak.social_network.entities.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl extends AbstractBaseDao<User> implements UserDao {

  @Override
  public void save(User obj) {
    saveOrUpdate(obj);
  }

  @Override
  public User getById(int id) {
    return getById(User.class, id);
  }

  @Override
  public List<User> getAll() {
    return getAll(User.class);
  }

  @Override
  public void update(User obj) {
    saveOrUpdate(obj);
  }

  @Override
  public void deleteById(int id) {
    delete(getById(User.class, id));
  }

}
