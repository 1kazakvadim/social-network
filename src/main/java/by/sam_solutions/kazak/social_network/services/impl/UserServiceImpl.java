package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.UserDao;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import by.sam_solutions.kazak.social_network.services.RoleService;
import by.sam_solutions.kazak.social_network.services.UserService;
import by.sam_solutions.kazak.social_network.services.VerificationTokenService;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDao userDao;

  @Autowired
  private RoleService roleService;

  @Autowired
  private ProfileService profileService;

  @Autowired
  private VerificationTokenService verificationTokenService;

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
    return userDao.findByEmail(email.toLowerCase());
  }

  @Override
  public boolean isEmailExists(String email) {
    logger.debug("check if user exists with email = {}", email);
    return userDao.isEmailExists(email.toLowerCase());
  }

  @Override
  public boolean isPasswordMatchConfirmPassword(String password, String confirmPassword) {
    return password.equals(confirmPassword);
  }

  @Override
  public void changePassword(User user, String password) {
    user.setPassword(passwordEncoder.encode(password));
    userDao.saveOrUpdate(user);
  }

  @Override
  public User registerUser(BasicInformation basicInformation, String email, String password) {
    logger.debug("register user with email = {}", email);
    User user = new User();
    user.setPassword(passwordEncoder.encode(password));
    user.setEmail(email.toLowerCase());
    user.setLocked(true);
    user.setRole(roleService.findByName("USER"));
    Profile profile = new Profile();
    profile.setUser(user);
    profile.setTimeRegistration(LocalDateTime.now());
    profile.setBasicInformation(basicInformation);
    profileService.saveOrUpdate(profile);
    return user;
  }

  @Override
  public void confirmRegisterUser(String token) {
    logger.debug("confirm register user with token = {}", token);
    User user = verificationTokenService.getUserByToken(token);
    user.setLocked(false);
    userDao.saveOrUpdate(user);
  }

}
