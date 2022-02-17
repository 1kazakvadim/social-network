package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
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

  private final Logger logger = LoggerFactory.getLogger(TokenServiceTest.class);

  @Autowired
  private TokenService tokenService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private UserService userService;

  private Token token;
  private User user;

  @BeforeTransaction
  public void addValue() {
    token = new Token();
    user = new User();
    user.setEmail("email");
    user.setPassword("password");
    user.setRole(roleService.findByName("USER"));
    user.setLocked(false);
    userService.saveOrUpdate(user);
    token.setToken("token");
    token.setUser(user);
    token.setExpiryDate(LocalDateTime.now().minusHours(1));
    tokenService.saveOrUpdate(token);
  }

  @AfterTransaction
  public void removeValues() {
    tokenService.deleteById(token.getId());
    userService.deleteById(user.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");
    token.setToken("updatedToken");
    token.setExpiryDate(LocalDateTime.now().plusDays(1));
    tokenService.saveOrUpdate(token);
    Token updatedToken = tokenService.getById(token.getId());
    assertEquals(updatedToken.getId(), token.getId());
    assertEquals(updatedToken.getToken(), token.getToken());
    assertEquals(updatedToken.getUser(), token.getUser());
    assertEquals(updatedToken.getExpiryDate(), token.getExpiryDate());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Token tokenById = tokenService.getById(token.getId());
    assertEquals(tokenById.getId(), token.getId());
    assertEquals(tokenById.getToken(), token.getToken());
    assertEquals(tokenById.getExpiryDate(), token.getExpiryDate());
  }

  @Test
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    tokenService.deleteById(token.getId());
    assertNull(tokenService.getById(token.getId()));
  }

  @Test
  @Rollback
  public void testDeleteByName() {
    logger.debug("Execute test: testDeleteByName()");
    tokenService.deleteByName(token.getToken());
    assertNull(tokenService.getByToken(token.getToken()));
  }

  @Test
  public void testCreateVerificationToken() {
    logger.debug("Execute test: testCreateVerificationToken()");
    Token token = tokenService.createVerificationToken(user);
    assertNotNull(token);
  }

  @Test
  public void testCreatePasswordResetToken() {
    logger.debug("Execute test: testCreatePasswordResetToken()");
    Token token = tokenService.createPasswordResetToken(user.getEmail());
    assertNotNull(token);
  }

  @Test(expected = PropertyValueException.class)
  public void testCreatePasswordResetTokenWithNonExistentEmail() {
    logger.debug("Execute test: testCreatePasswordResetTokenWithNonExistentEmail()");
    tokenService.createPasswordResetToken("nonExistEmail");
  }

  @Test
  public void testGetByToken() {
    logger.debug("Execute test: testGetByToken()");
    Token tokenByName = tokenService.getByToken(token.getToken());
    assertEquals(tokenByName.getId(), token.getId());
    assertEquals(tokenByName.getToken(), token.getToken());
    assertEquals(tokenByName.getExpiryDate(), token.getExpiryDate());
  }

  @Test
  public void testIsTokenExpiredTrue() {
    logger.debug("Execute test: testIsTokenExpiredTrue()");
    assertTrue(tokenService.isTokenExpired(token.getToken()));
  }

  @Test
  public void testIsTokenExpiredFalse() {
    logger.debug("Execute test: is_token_expired()");
    token.setExpiryDate(LocalDateTime.now().plusHours(1));
    tokenService.saveOrUpdate(token);
    assertFalse(tokenService.isTokenExpired(token.getToken()));
  }

  @Test
  public void constructVerificationTokenEmailEnLocale() {
    logger.debug("Execute test: constructVerificationTokenEmailWithEnLocale()");
    SimpleMailMessage simpleMailMessage = tokenService.constructVerificationTokenEmail("appUrl",
        token, user, new Locale("en"));
    assertNotNull(simpleMailMessage);
  }

  @Test
  public void constructVerificationTokenEmailRuLocale() {
    logger.debug("Execute test: constructVerificationTokenEmailWithRuLocale()");
    SimpleMailMessage simpleMailMessage = tokenService.constructVerificationTokenEmail("appUrl",
        token, user, new Locale("ru"));
    assertNotNull(simpleMailMessage);
  }

  @Test
  public void constructPasswordResetTokenEmailEnLocale() {
    logger.debug("Execute test: constructPasswordResetTokenEmailWithEnLocale()");
    SimpleMailMessage simpleMailMessage = tokenService.constructPasswordResetTokenEmail("appUrl",
        token, user, new Locale("en"));
    assertNotNull(simpleMailMessage);
  }

  @Test
  public void constructPasswordResetTokenEmailRuLocale() {
    logger.debug("Execute test: constructPasswordResetTokenEmailWithRuLocale()");
    SimpleMailMessage simpleMailMessage = tokenService.constructPasswordResetTokenEmail("appUrl",
        token, user, new Locale("ru"));
    assertNotNull(simpleMailMessage);
  }

}
