package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.ProfileDao;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

  private final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

  private static final String SPECIAL_CHARACTERS_PATTERN = "[^A-Za-z0-9]";
  private static final int MAX_FIELD_LENGTH = 255;

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
    logger.debug("get profile with email = {}", email);
    return profileDao.getProfileByEmail(email);
  }

  @Override
  public Profile getProfileByUserId(Long id) {
    logger.debug("get profile by user with id = {}", id);
    return profileDao.getProfileByUserId(id);
  }

  @Override
  public boolean isFieldContainsSpecialCharacters(String string) {
    Pattern pattern = Pattern.compile(SPECIAL_CHARACTERS_PATTERN);
    Matcher matcher = pattern.matcher(string);
    return matcher.find();
  }

  @Override
  public boolean isValidMaxFieldLength(String string) {
    return string.length() >= MAX_FIELD_LENGTH;
  }

}
