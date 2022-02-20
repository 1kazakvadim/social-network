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
import java.util.Locale;
import org.hibernate.PropertyValueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppContextConfig.class)
@Transactional
public class TokenServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(TokenServiceTest.class);

  private static final String DEFAULT_TEST_TOKEN_NAME = "testName";
  private static final LocalDateTime DEFAULT_TEST_TOKEN_EXPIRY_DATE = LocalDateTime.now()
      .plusDays(1);
  private static final String DEFAULT_TEST_USER_EMAIL = "testEmail";
  private static final String DEFAULT_TEST_USER_NON_EXISTENT_EMAIL = "testNonExistEmail";

  @Autowired
  private TokenService tokenService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private UserService userService;

  private Token token;
  private User user;
  private Role role;

  @BeforeTransaction
  public void addValue() {
    token = new Token();
    user = new User();
    role = new Role();
    role.setName("name");
    roleService.saveOrUpdate(role);
    user.setEmail(DEFAULT_TEST_USER_EMAIL);
    user.setPassword("password");
    user.setRole(role);
    user.setLocked(false);
    userService.saveOrUpdate(user);
    token.setToken(DEFAULT_TEST_TOKEN_NAME);
    token.setUser(user);
    token.setExpiryDate(DEFAULT_TEST_TOKEN_EXPIRY_DATE);
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

    final String updatedTestTokenName = "updatedTestName";
    final LocalDateTime updatedTestExpiryDate = LocalDateTime.now()
        .plusDays(2);

    Token token = new Token();
    token.setId(this.token.getId());
    token.setToken(updatedTestTokenName);
    token.setUser(user);
    token.setExpiryDate(updatedTestExpiryDate);
    tokenService.saveOrUpdate(token);
    Token updatedToken = tokenService.getById(token.getId());
    assertNotNull(updatedToken);
    assertEquals(updatedTestTokenName, updatedToken.getToken());
    assertEquals(updatedTestExpiryDate, updatedToken.getExpiryDate());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Token tokenById = tokenService.getById(token.getId());
    assertNotNull(tokenById);
    assertEquals(tokenById.getId(), token.getId());
    assertEquals(tokenById.getToken(), DEFAULT_TEST_TOKEN_NAME);
    assertEquals(tokenById.getExpiryDate(), DEFAULT_TEST_TOKEN_EXPIRY_DATE);
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    tokenService.deleteById(token.getId());
    assertNull(tokenService.getById(token.getId()));
  }

  @Test
  @Rollback
  public void testDeleteByName() {
    logger.debug("Execute test: testDeleteByName()");
    tokenService.deleteByName(DEFAULT_TEST_TOKEN_NAME);
    assertNull(tokenService.getByToken(DEFAULT_TEST_TOKEN_NAME));
  }

  @Test
  @Rollback
  public void testCreateVerificationToken() {
    logger.debug("Execute test: testCreateVerificationToken()");
    Token token = tokenService.createVerificationToken(user);
    assertNotNull(token);
  }

  @Test(expected = PropertyValueException.class)
  public void testCreatePasswordResetTokenWithNonExistentEmail() {
    logger.debug("Execute test: testCreatePasswordResetTokenWithNonExistentEmail()");
    tokenService.createPasswordResetToken(DEFAULT_TEST_USER_NON_EXISTENT_EMAIL);
  }

  @Test
  public void testGetByToken() {
    logger.debug("Execute test: testGetByToken()");
    Token tokenByName = tokenService.getByToken(DEFAULT_TEST_TOKEN_NAME);
    assertNotNull(tokenByName);
    assertEquals(tokenByName.getId(), token.getId());
    assertEquals(tokenByName.getToken(), DEFAULT_TEST_TOKEN_NAME);
    assertEquals(tokenByName.getExpiryDate(), DEFAULT_TEST_TOKEN_EXPIRY_DATE);
  }

  @Test
  public void testIsTokenExpiredFalse() {
    logger.debug("Execute test: testIsTokenExpiredFalse()");
    assertFalse(tokenService.isTokenExpired(DEFAULT_TEST_TOKEN_NAME));
  }

  @Test
  @Rollback
  public void testIsTokenExpiredTrue() {
    logger.debug("Execute test: testIsTokenExpiredTrue()");

    final LocalDateTime testFalseExpiryDate = LocalDateTime.now()
        .minusDays(2);

    token.setExpiryDate(testFalseExpiryDate);
    tokenService.saveOrUpdate(token);
    assertEquals(testFalseExpiryDate, token.getExpiryDate());
    assertTrue(tokenService.isTokenExpired(token.getToken()));
  }

  @Test
  public void constructVerificationTokenEmail() {
    logger.debug("Execute test: constructVerificationTokenEmailWithEnLocale()");
    SimpleMailMessage simpleMailMessage = tokenService.constructVerificationTokenEmail("appUrl",
        token, user, new Locale("en"));
    assertNotNull(simpleMailMessage);
  }

  @Test
  public void constructPasswordResetTokenEmail() {
    logger.debug("Execute test: constructPasswordResetTokenEmailWithEnLocale()");
    SimpleMailMessage simpleMailMessage = tokenService.constructPasswordResetTokenEmail("appUrl",
        token, user, new Locale("en"));
    assertNotNull(simpleMailMessage);
  }

}
