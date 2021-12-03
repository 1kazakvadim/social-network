package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.UserDao;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.services.UserService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  public UserServiceImpl(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public void save(User user) {
    userDao.save(user);
  }

  @Override
  public void update(User user) {
    userDao.update(user);
  }

  @Override
  public User getById(Integer id) {
    return userDao.getById(id);
  }

  @Override
  public List<User> getAll() {
    return userDao.getAll();
  }

  @Override
  public void deleteById(Integer id) {
    userDao.deleteById(id);
  }

}
