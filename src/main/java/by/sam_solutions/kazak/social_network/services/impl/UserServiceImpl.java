package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.UserDao;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import by.sam_solutions.kazak.social_network.services.RoleService;
import by.sam_solutions.kazak.social_network.services.UserService;
import by.sam_solutions.kazak.social_network.services.TokenService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

  private static final String PASSWORD_PATTERN =
      "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
  private static final String EMAIL_PATTERN =
      "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserDao userDao;

  @Autowired
  private RoleService roleService;

  @Autowired
  private ProfileService profileService;

  @Autowired
  private TokenService tokenService;

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
    logger.debug("check if user exists with email = {}", email.toLowerCase());
    return userDao.isEmailExists(email);
  }

  @Override
  public boolean isEmailValid(String email) {
    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email.toLowerCase());
    return matcher.matches();
  }

  @Override
  public boolean isPasswordValid(String password) {
    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
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
  public User registerUser(Profile profile) {
    logger.debug("register user with email = {}", profile.getUser().getEmail());
    User user = profile.getUser();
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEmail(user.getEmail().toLowerCase());
    user.setLocked(true);
    user.setRole(roleService.findByName("USER"));
    profile.setUser(user);
    profile.setTimeRegistration(LocalDateTime.now());
    profile.setUpdateTime(LocalDateTime.now());
    profileService.saveOrUpdate(profile);
    return user;
  }

  @Override
  public void confirmRegisterUser(String token) {
    logger.debug("confirm register user with token = {}", token);
    User user = tokenService.getUserByToken(token);
    user.setLocked(false);
    userDao.saveOrUpdate(user);
  }

}
