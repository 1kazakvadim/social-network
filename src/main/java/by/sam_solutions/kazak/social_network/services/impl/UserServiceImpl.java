package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.UserDao;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.services.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserDao userDao;

  @Override
  public void saveOrUpdate(User user) {
    logger.debug("saveOrUpdate({})", user);
    userDao.saveOrUpdate(user);
  }

  @Override
  public User getById(Long id) {
    logger.debug("get user by id = {}", id);
    return userDao.getById(id);
  }

  @Override
  public List<User> getAll() {
    logger.debug("get all users");
    return userDao.getAll();
  }

  @Override
  public void deleteById(Long id) {
    logger.debug("delete user with id = {}", id);
    userDao.deleteById(id);
  }

  @Override
  public User findByEmail(String email) {
    logger.debug("get user with email = {}", email);
    return userDao.findByEmail(email);
  }

}
