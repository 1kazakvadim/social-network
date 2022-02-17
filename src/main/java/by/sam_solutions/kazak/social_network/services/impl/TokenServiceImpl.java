package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.TokenDao;
import by.sam_solutions.kazak.social_network.entities.Token;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.services.TokenService;
import by.sam_solutions.kazak.social_network.services.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenServiceImpl implements TokenService {

  private final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

  private static final long DAYS_OF_VERIFICATION_TOKEN_EXPIRATION_DATE = 1;
  private static final long HOURS_OF_PASSWORD_RESET_TOKEN_EXPIRATION_DATE = 1;

  @Autowired
  private TokenDao tokenDao;

  @Autowired
  private UserService userService;

  @Autowired
  private MessageSource messageSource;

  @Override
  @Transactional
  public void saveOrUpdate(Token token) {
    logger.debug("saveOrUpdate({})", token);
    tokenDao.saveOrUpdate(token);
  }

  @Override
  @Transactional(readOnly = true)
  public Token getById(Long id) {
    logger.debug("get token by id = {}", id);
    return tokenDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Token> getAll() {
    logger.debug("get all tokens");
    return tokenDao.getAll();
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete token with id = {}", id);
    tokenDao.deleteById(id);
  }

  @Override
  @Transactional
  public void deleteByName(String token) {
    logger.debug("delete token with name = {}", token);
    tokenDao.deleteByName(token);
  }

  @Override
  @Transactional
  public Token createVerificationToken(User user) {
    logger.debug("create verification token for user with id = {}", user.getId());
    Token token = new Token();
    token.setToken(UUID.randomUUID().toString());
    token.setUser(user);
    token.setExpiryDate(LocalDateTime.now().plusDays(DAYS_OF_VERIFICATION_TOKEN_EXPIRATION_DATE));
    tokenDao.saveOrUpdate(token);
    return token;
  }

  @Override
  @Transactional
  public Token createPasswordResetToken(String email) {
    logger.debug("create password reset token for user with email = {}", email);
    Token token = new Token();
    token.setToken(UUID.randomUUID().toString());
    token.setUser(userService.getByEmail(email.toLowerCase()));
    token.setExpiryDate(
        LocalDateTime.now().plusHours(HOURS_OF_PASSWORD_RESET_TOKEN_EXPIRATION_DATE));
    tokenDao.saveOrUpdate(token);
    return token;
  }

  @Override
  @Transactional(readOnly = true)
  public Token getByToken(String token) {
    logger.debug("get token = {}", token);
    return tokenDao.getByToken(token);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean isTokenExpired(String token) {
    logger.debug("is token expired = {}", token);
    return LocalDateTime.now().isAfter(tokenDao.getByToken(token).getExpiryDate());
  }

  @Override
  public SimpleMailMessage constructVerificationTokenEmail(String appUrl, Token token,
      User user, Locale locale) {
    logger.debug("construct verification token email to user with id = {}, token name {}", user.getId(),
        token.getToken());
    String confirmationUrl
        = appUrl + "/confirm-register?token=" + token.getToken();
    String message = messageSource.getMessage("verificationToken.email.text", null, locale);
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(user.getEmail());
    email.setSubject(messageSource.getMessage("verificationToken.email.subject", null, locale));
    email.setText(message + System.lineSeparator() + "http://localhost:8080" + confirmationUrl);
    return email;
  }

  @Override
  public SimpleMailMessage constructPasswordResetTokenEmail(String appUrl, Token token, User user,
      Locale locale) {
    logger.debug("construct password reset token email to user with id = {}, token name {}", user.getId(),
        token.getToken());
    String resetUrl
        = appUrl + "/confirm-reset?token=" + token.getToken();
    String message = messageSource.getMessage("resetToken.email.text", null, locale);
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(user.getEmail());
    email.setSubject(messageSource.getMessage("resetToken.email.subject", null, locale));
    email.setText(message + System.lineSeparator() + "http://localhost:8080" + resetUrl);
    return email;
  }

}
