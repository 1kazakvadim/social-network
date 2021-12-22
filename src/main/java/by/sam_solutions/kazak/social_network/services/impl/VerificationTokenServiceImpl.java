package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.VerificationTokenDao;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.entities.VerificationToken;
import by.sam_solutions.kazak.social_network.services.VerificationTokenService;
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
public class VerificationTokenServiceImpl implements VerificationTokenService {

  private final Logger logger = LoggerFactory.getLogger(VerificationTokenServiceImpl.class);

  @Autowired
  private VerificationTokenDao verificationTokenDao;

  @Override
  public void saveOrUpdate(VerificationToken verificationToken) {
    logger.debug("saveOrUpdate({})", verificationToken);
    verificationTokenDao.saveOrUpdate(verificationToken);
  }

  @Override
  public VerificationToken getById(Long id) {
    logger.debug("get verification token by id = {}", id);
    return verificationTokenDao.getById(id);
  }

  @Override
  public List<VerificationToken> getAll() {
    logger.debug("get all verification tokens");
    return verificationTokenDao.getAll();
  }

  @Override
  public void deleteById(Long id) {
    logger.debug("delete verification token with id = {}", id);
    verificationTokenDao.deleteById(id);
  }

  @Override
  public VerificationToken createVerificationToken(User user) {
    VerificationToken verificationToken = new VerificationToken();
    verificationToken.setToken(UUID.randomUUID().toString());
    verificationToken.setUser(user);
    verificationToken.setExpiryDate(LocalDateTime.now().plusDays(1));
    verificationTokenDao.saveOrUpdate(verificationToken);
    return verificationToken;
  }

  @Override
  public VerificationToken getByToken(String token) {
    logger.debug("get verification token = {}", token);
    return verificationTokenDao.getByToken(token);
  }

  @Override
  public User getUserByToken(String token) {
    logger.debug("get user with token = {}", token);
    return verificationTokenDao.getUserByToken(token);
  }

  @Override
  public boolean isTokenExpired(String token) {
    return LocalDateTime.now().isAfter(verificationTokenDao.getByToken(token).getExpiryDate());
  }

  @Override
  public SimpleMailMessage constructVerificationTokenEmail(String appUrl, VerificationToken token,
      User user) {
    String confirmationUrl
        = appUrl + "/confirm-register?token=" + token.getToken();
    String message = "To confirm your account, please click here:";
    SimpleMailMessage email = new SimpleMailMessage();
    email.setTo(user.getEmail());
    email.setSubject("Registration Confirmation");
    email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
    return email;
  }

}
