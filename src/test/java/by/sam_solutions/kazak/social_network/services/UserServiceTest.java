package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.Role;
import by.sam_solutions.kazak.social_network.entities.Token;
import by.sam_solutions.kazak.social_network.entities.User;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppContextConfig.class)
@Transactional
public class UserServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(ProfileServiceTest.class);

  private static final String DEFAULT_TEST_USER_EMAIL = "test@email.com";
  private static final String DEFAULT_TEST_USER_EMAIL_NON_EXISTS = "testNonExist@email.com";
  private static final String DEFAULT_TEST_USER_NEW_EMAIL = "testNew@email.com";
  private static final String DEFAULT_TEST_USER_PASSWORD = "JI4'c;hj+Ju85PW";
  private static final String DEFAULT_TEST_NON_USER_PASSWORD = "KI4'f;hj+Qu09MN";
  private static final String DEFAULT_TEST_USER_PASSWORD_ENCRYPTED = "$2a$08$D9pjrrYIyPeRbacVifMGMe2r8fxr/Cs0x2CHdlleTPZDf3DRw9.Ku";
  private static final String DEFAULT_TEST_USER_PASSWORD_INVALID = "invalid";
  private static final String DEFAULT_TEST_USER_NEW_PASSWORD = "XZ4'c;df+Ju12PW";
  private static final String DEFAULT_TEST_TOKEN_NAME = "testName";

  @Autowired
  private UserService userService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private TokenService tokenService;

  private User user;
  private Role role;
  private Token token;

  @BeforeTransaction
  public void addValues() {
    user = new User();
    role = new Role();
    token = new Token();
    role.setName("USER");
    roleService.saveOrUpdate(role);
    user.setEmail(DEFAULT_TEST_USER_EMAIL);
    user.setPassword(DEFAULT_TEST_USER_PASSWORD_ENCRYPTED);
    user.setRole(role);
    user.setLocked(true);
    userService.saveOrUpdate(user);
    token.setToken(DEFAULT_TEST_TOKEN_NAME);
    token.setUser(user);
    token.setExpiryDate(LocalDateTime.now().plusDays(1));
    tokenService.saveOrUpdate(token);
  }

  @AfterTransaction
  public void removeValues() {
    tokenService.deleteById(token.getId());
    userService.deleteById(user.getId());
    roleService.deleteById(role.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");

    final String updatedTestUserEmail = "updatedTest@email.com";
    final String updatedTestUserPassword = "updatedUD2Ts8bu";

    User user = new User();
    user.setId(this.user.getId());
    user.setEmail(updatedTestUserEmail);
    user.setPassword(updatedTestUserPassword);
    userService.saveOrUpdate(user);
    User updatedUser = userService.getById(user.getId());
    assertNotNull(updatedUser);
    assertEquals(updatedTestUserEmail, user.getEmail());
    assertEquals(updatedTestUserPassword, user.getPassword());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    User userById = userService.getById(user.getId());
    assertNotNull(userById);
    assertEquals(userById.getId(), user.getId());
    assertEquals(userById.getEmail(), DEFAULT_TEST_USER_EMAIL);
    assertEquals(userById.getPassword(), DEFAULT_TEST_USER_PASSWORD_ENCRYPTED);
  }

  @Test
  public void testGetAll() {
    logger.debug("Execute test: testGetAll()");
    List<User> users = userService.getAll();
    assertNotNull(users);
    assertEquals(1, users.size());
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    userService.deleteById(user.getId());
    assertNull(userService.getById(user.getId()));
  }

  @Test
  public void testGetByEmail() {
    logger.debug("Execute test: testGetByEmail()");
    User userByEmail = userService.getByEmail(DEFAULT_TEST_USER_EMAIL);
    assertNotNull(userByEmail);
    assertEquals(userByEmail.getEmail(), DEFAULT_TEST_USER_EMAIL);
  }

  @Test
  public void testIsEmailExistsTrue() {
    logger.debug("Execute test: testIsEmailExistsTrue()");
    assertTrue(userService.isEmailExists(DEFAULT_TEST_USER_EMAIL));
  }

  @Test
  public void testIsEmailExistsFalse() {
    logger.debug("Execute test: testIsEmailExistsTrue()");
    assertFalse(userService.isEmailExists(DEFAULT_TEST_USER_EMAIL_NON_EXISTS));
  }

  @Test
  public void testIsPasswordValidTrue() {
    logger.debug("Execute test: testIsPasswordValidTrue()");
    assertTrue(userService.isPasswordValid(DEFAULT_TEST_USER_PASSWORD));
  }

  @Test
  public void testIsPasswordValidFalse() {
    logger.debug("Execute test: testIsPasswordValidFalse()");
    assertFalse(userService.isPasswordValid(DEFAULT_TEST_USER_PASSWORD_INVALID));
  }

  @Test
  public void testIsUserPasswordTrue() {
    logger.debug("Execute test: testIsUserPasswordTrue()");
    assertTrue(userService.isUserPassword(user.getId(), DEFAULT_TEST_USER_PASSWORD));
  }

  @Test
  public void testIsUserPasswordFalse() {
    logger.debug("Execute test: testIsUserPasswordFalse()");
    assertFalse(userService.isUserPassword(user.getId(), DEFAULT_TEST_NON_USER_PASSWORD));
  }

  @Test
  public void testIsPasswordMatchConfirmPasswordTrue() {
    logger.debug("Execute test: testIsPasswordMatchConfirmPasswordTrue()");
    assertTrue(userService.isPasswordMatchConfirmPassword(DEFAULT_TEST_USER_PASSWORD,
        DEFAULT_TEST_USER_PASSWORD));
  }

  @Test
  public void testIsPasswordMatchConfirmPasswordFalse() {
    logger.debug("Execute test: testIsPasswordMatchConfirmPasswordFalse()");
    assertFalse(userService.isPasswordMatchConfirmPassword(DEFAULT_TEST_USER_PASSWORD,
        DEFAULT_TEST_NON_USER_PASSWORD));
  }

  @Test
  public void testChangePassword() {
    logger.debug("Execute test: testChangePassword()");
    userService.changePassword(user, DEFAULT_TEST_USER_NEW_PASSWORD);
    assertTrue(userService.isUserPassword(user.getId(), DEFAULT_TEST_USER_NEW_PASSWORD));
  }

  @Test
  public void testChangeEmail() {
    logger.debug("Execute test: testChangeEmail()");
    userService.changeEmail(user, DEFAULT_TEST_USER_NEW_EMAIL);
    User userAfterChangeEmail = userService.getById(user.getId());
    assertNotNull(userAfterChangeEmail);
    assertEquals(userAfterChangeEmail.getEmail(), DEFAULT_TEST_USER_NEW_EMAIL);
  }

  @Test
  public void testDisableUser() {
    logger.debug("Execute test: testDisableUser()");
    userService.disableUser(user.getId());
    User disabledUser = userService.getById(user.getId());
    assertNotNull(disabledUser);
    assertTrue(disabledUser.isLocked());
  }

  @Test
  @Rollback
  public void testConfirmRegisterUser() {
    logger.debug("Execute test: testConfirmRegisterUser()");
    User user = userService.getByToken(DEFAULT_TEST_TOKEN_NAME);
    assertNotNull(user);
    userService.confirmRegisterUser(DEFAULT_TEST_TOKEN_NAME);
    assertFalse(user.isLocked());
  }


}
