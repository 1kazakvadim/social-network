package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.ProfileDao;
import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileServiceImpl implements ProfileService {

  private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);

  private static final String SPECIAL_CHARACTERS_PATTERN = "[^A-Za-zа-яА-ЯёЁ0-9]";

  @Autowired
  private ProfileDao profileDao;

  @Override
  @Transactional
  public void saveOrUpdate(Profile profile) {
    logger.debug("saveOrUpdate({})", profile);
    profileDao.saveOrUpdate(profile);
  }

  @Override
  @Transactional(readOnly = true)
  public Profile getById(Long id) {
    logger.debug("get profile by id = {}", id);
    return profileDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Profile> getAll() {
    logger.debug("get all profiles");
    return profileDao.getAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Profile> getAll(Integer page, Integer size) {
    logger.debug("get all profiles");
    return profileDao.getAll(page, size);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete profile with id = {}", id);
    profileDao.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Profile getProfileByUserId(Long id) {
    logger.debug("get profile by user with id = {}", id);
    return profileDao.getProfileByUserId(id);
  }

  @Override
  public List<Profile> getUniqueFriendsProfiles(List<Friend> friends, Long id) {
    logger.debug("get unique friends profiles by id = {}", id);
    Set<Profile> friendsProfiles = new HashSet<>();
    for (Friend friend : friends) {
      if (!Objects.equals(friend.getAcceptRequestProfile().getId(), id)) {
        friendsProfiles.add(friend.getAcceptRequestProfile());
      }
      if (!Objects.equals(friend.getMakeRequestProfile().getId(), id)) {
        friendsProfiles.add(friend.getMakeRequestProfile());
      }
    }
    List<Profile> profiles = new ArrayList<>(friendsProfiles);
    return profiles.stream().sorted(Comparator.comparing(Profile::getId)).collect(
        Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public List<Profile> searchForProfiles(String search) throws Exception {
    logger.debug("search for profiles by = {}", search);
    return profileDao.searchForProfiles(search);
  }

  @Override
  public boolean isFieldContainsSpecialCharacters(String string) {
    logger.debug("is field contains special characters = {}", string);
    Pattern pattern = Pattern.compile(SPECIAL_CHARACTERS_PATTERN);
    Matcher matcher = pattern.matcher(string);
    return matcher.find();
  }

  @Override
  @Transactional(readOnly = true)
  public Long countProfiles() {
    logger.debug("count profiles");
    return profileDao.countProfiles();
  }

}
