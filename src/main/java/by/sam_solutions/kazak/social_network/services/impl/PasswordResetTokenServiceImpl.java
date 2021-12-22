package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.PasswordResetTokenDao;
import by.sam_solutions.kazak.social_network.entities.PasswordResetToken;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.services.PasswordResetTokenService;
import by.sam_solutions.kazak.social_network.services.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

  private final Logger logger = LoggerFactory.getLogger(PasswordResetTokenServiceImpl.class);

  @Autowired
  private PasswordResetTokenDao passwordResetTokenDao;

  @Autowired
  private UserService userService;

  @Override
  public void saveOrUpdate(PasswordResetToken passwordResetToken) {
    logger.debug("saveOrUpdate({})", passwordResetToken);
    passwordResetTokenDao.saveOrUpdate(passwordResetToken);
  }

  @Override
  public PasswordResetToken getById(Long id) {
    logger.debug("get password reset token by id = {}", id);
    return passwordResetTokenDao.getById(id);
  }

  @Override
  public List<PasswordResetToken> getAll() {
    logger.debug("get all password reset tokens");
    return passwordResetTokenDao.getAll();
  }

  @Override
  public void deleteById(Long id) {
    logger.debug("delete password reset token with id = {}", id);
    passwordResetTokenDao.deleteById(id);
  }

  @Override
  public PasswordResetToken getByToken(String token) {
    logger.debug("get password reset token with token = {}", token);
    return passwordResetTokenDao.getByToken(token);
  }

  @Override
  public User getUserByToken(String token) {
    logger.debug("get user with token = {}", token);
    return passwordResetTokenDao.getUserByToken(token);
  }

  @Override
  public PasswordResetToken createPasswordResetTokenForUser(String email) {
    PasswordResetToken passwordResetToken = new PasswordResetToken();
    passwordResetToken.setToken(UUID.randomUUID().toString());
    passwordResetToken.setUser(userService.findByEmail(email.toLowerCase()));
    passwordResetToken.setExpiryDate(LocalDateTime.now().plusHours(1));
    passwordResetTokenDao.saveOrUpdate(passwordResetToken);
    return passwordResetToken;
  }

  @Override
  public SimpleMailMessage constructPasswordResetTokenEmail(String appUrl, PasswordResetToken token,
      User user) {
    String confirmationUrl
        = appUrl + "/confirm-reset?token=" + token.getToken();
    String message = "To reset your password, please click here:";
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(user.getEmail());
    email.setSubject("Reset Password");
    email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
    return email;
  }

  @Override
  public boolean isTokenExpired(String token) {
    return LocalDateTime.now().isAfter(passwordResetTokenDao.getByToken(token).getExpiryDate());
  }

}
