package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.VerificationTokenDao;
import by.sam_solutions.kazak.social_network.entities.VerificationToken;
import by.sam_solutions.kazak.social_network.services.VerificationTokenService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
  public VerificationToken findByToken(String token) {
    logger.debug("get verification token with token = {}", token);
    return verificationTokenDao.findByToken(token);
  }

}
