package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.ProfileDao;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

  private final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

  @Autowired
  private ProfileDao profileDao;

  @Override
  public void saveOrUpdate(Profile profile) {
    logger.debug("saveOrUpdate({})", profile);
    profileDao.saveOrUpdate(profile);
  }

  @Override
  public Profile getById(Long id) {
    logger.debug("get profile by id = {}", id);
    return profileDao.getById(id);
  }

  @Override
  public List<Profile> getAll() {
    logger.debug("get all profiles");
    return profileDao.getAll();
  }

  @Override
  public void deleteById(Long id) {
    logger.debug("delete profile with id = {}", id);
    profileDao.deleteById(id);
  }

  @Override
  public Profile getProfileByEmail(String email) {
    return profileDao.getProfileByEmail(email);
  }

}
