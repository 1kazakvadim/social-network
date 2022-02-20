package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.UserDao;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import by.sam_solutions.kazak.social_network.services.RoleService;
import by.sam_solutions.kazak.social_network.services.TokenService;
import by.sam_solutions.kazak.social_network.services.UserService;
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
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private static final String PASSWORD_PATTERN =
      "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
  private static final String EMAIL_PATTERN =
      "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
  private static final String ROLE_USER = "USER";
  private static final String DEFAULT_PROFILE_PHOTO_NAME = "default-profile-photo";

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
  @Transactional
  public void saveOrUpdate(User user) {
    logger.debug("saveOrUpdate({})", user);
    userDao.saveOrUpdate(user);
  }

  @Override
  @Transactional(readOnly = true)
  public User getById(Long id) {
    logger.debug("get user by id = {}", id);
    return userDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> getAll() {
    logger.debug("get all users");
    return userDao.getAll();
  }

  @Override
  @Transactional(readOnly = true)
  public User getByToken(String token) {
    logger.debug("get user with token = {}", token);
    return userDao.getByToken(token);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete user with id = {}", id);
    userDao.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public User getByEmail(String email) {
    logger.debug("get user with email = {}", email);
    return userDao.getByEmail(email.toLowerCase());
  }

  @Override
  @Transactional(readOnly = true)
  public boolean isEmailExists(String email) {
    logger.debug("check if user exists with email = {}", email);
    return userDao.isEmailExists(email);
  }

  @Override
  public boolean isEmailValid(String email) {
    logger.debug("is email valid = {}", email);
    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    Matcher matcher = pattern.matcher(email.toLowerCase());
    return matcher.matches();
  }

  @Override
  public boolean isPasswordValid(String password) {
    logger.debug("is password valid = {}", password);
    Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }

  @Override
  @Transactional(readOnly = true)
  public boolean isUserPassword(Long id, String password) {
    logger.debug("is user password = {}", password);
    User user = userDao.getById(id);
    return passwordEncoder.matches(password, user.getPassword());
  }

  @Override
  public boolean isPasswordMatchConfirmPassword(String password, String confirmPassword) {
    logger.debug("is password = {} match confirm password = {}", password, confirmPassword);
    return password.equals(confirmPassword);
  }

  @Override
  @Transactional
  public void changePassword(User user, String password) {
    logger.debug("change password to user with id = {}", user.getId());
    user.setPassword(passwordEncoder.encode(password));
    userDao.saveOrUpdate(user);
  }

  @Override
  @Transactional
  public void changeEmail(User user, String email) {
    logger.debug("change email to user with id = {}", user.getId());
    user.setEmail(email);
    userDao.saveOrUpdate(user);
  }

  @Override
  @Transactional
  public void disableUser(Long id) {
    logger.debug("disable user with id = {}", id);
    User user = userDao.getById(id);
    user.setLocked(true);
    userDao.saveOrUpdate(user);
  }

  @Override
  @Transactional
  public User registerUser(Profile profile) {
    logger.debug("register user with email = {}", profile.getUser().getEmail());
    User user = profile.getUser();
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEmail(user.getEmail().toLowerCase());
    user.setLocked(true);
    user.setRole(roleService.findByName(ROLE_USER));
    profile.setUser(user);
    profile.setProfilePhotoName(DEFAULT_PROFILE_PHOTO_NAME);
    profile.setTimeRegistration(LocalDateTime.now());
    profile.setUpdateTime(LocalDateTime.now());
    profileService.saveOrUpdate(profile);
    return user;
  }

  @Override
  @Transactional
  public void confirmRegisterUser(String token) {
    logger.debug("confirm register user with token = {}", token);
    User user = userDao.getByToken(token);
    user.setLocked(false);
    userDao.saveOrUpdate(user);
    tokenService.deleteByName(token);
  }

}
