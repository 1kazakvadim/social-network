package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.UserDao;
import by.sam_solutions.kazak.social_network.entities.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImpl extends AbstractBaseDao<User> implements UserDao {

  @Override
  public void saveOrUpdate(User obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public User getById(Long id) {
    return getById(User.class, id);
  }

  @Override
  public List<User> getAll() {
    return getAll(User.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(User.class, id));
  }

}
