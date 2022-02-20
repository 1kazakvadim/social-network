package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.Role;
import by.sam_solutions.kazak.social_network.entities.User;
import java.time.LocalDate;
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
public class ProfileServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(ProfileServiceTest.class);

  private static final String DEFAULT_TEST_PROFILE_JOB_TITLE = "testJobTitle";
  private static final String DEFAULT_TEST_PROFILE_CITY = "testCity";
  private static final String DEFAULT_TEST_USER_EMAIL = "test@email.com";
  private static final String DEFAULT_TEST_BASIC_INFORMATION_FIRSTNAME = "testFirstname";
  private static final String DEFAULT_TEST_FIELD_WITHOUT_SPECIAL_CHARACTERS = "testField";
  private static final String DEFAULT_TEST_FIELD_WITH_SPECIAL_CHARACTERS = "*field%$";

  @Autowired
  private ProfileService profileService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private BasicInformationService basicInformationService;

  @Autowired
  private UserService userService;

  private Profile profile;
  private BasicInformation basicInformation;
  private User user;
  private Role role;

  @BeforeTransaction
  public void addValues() {
    profile = new Profile();
    basicInformation = new BasicInformation();
    user = new User();
    role = new Role();
    role.setName("USER");
    roleService.saveOrUpdate(role);
    user.setEmail(DEFAULT_TEST_USER_EMAIL);
    user.setPassword("password");
    user.setRole(role);
    user.setLocked(false);
    basicInformation.setFirstname(DEFAULT_TEST_BASIC_INFORMATION_FIRSTNAME);
    basicInformation.setLastname("lastname");
    basicInformation.setGender("MALE");
    basicInformation.setBirthday(LocalDate.now().minusYears(1));
    profile.setUser(user);
    profile.setBasicInformation(basicInformation);
    profile.setJobTitle(DEFAULT_TEST_PROFILE_JOB_TITLE);
    profile.setCity(DEFAULT_TEST_PROFILE_CITY);
    profile.setTimeRegistration(LocalDateTime.now());
    profile.setUpdateTime(LocalDateTime.now());
    profileService.saveOrUpdate(profile);
  }

  @AfterTransaction
  public void removeValues() {
    profileService.deleteById(profile.getId());
    basicInformationService.deleteById(basicInformation.getId());
    userService.deleteById(user.getId());
    roleService.deleteById(role.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");

    final String updatedTestJobTitle = "updatedTestJobTitle";
    final String updatedTestCity = "updatedTestCity";

    Profile profile = new Profile();
    profile.setId(this.profile.getId());
    profile.setJobTitle(updatedTestJobTitle);
    profile.setCity(updatedTestCity);
    profileService.saveOrUpdate(profile);
    Profile updatedProfile = profileService.getById(profile.getId());
    assertNotNull(updatedProfile);
    assertEquals(updatedTestJobTitle, updatedProfile.getJobTitle());
    assertEquals(updatedTestCity, updatedProfile.getCity());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Profile profileById = profileService.getById(profile.getId());
    assertNotNull(profileById);
    assertEquals(profileById.getCity(), DEFAULT_TEST_PROFILE_CITY);
    assertEquals(profileById.getCity(), DEFAULT_TEST_PROFILE_CITY);
  }

  @Test
  public void testGetAll() {
    logger.debug("Execute test: testGetAll()");
    List<Profile> profiles = profileService.getAll();
    assertNotNull(profiles);
    assertEquals(1, profiles.size());
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    profileService.deleteById(profile.getId());
    assertNull(profileService.getById(profile.getId()));
  }

  @Test
  public void testGetProfileByUserId() {
    logger.debug("Execute test: testGetProfileByUserId()");
    Profile profileByUserId = profileService.getProfileByUserId(user.getId());
    assertNotNull(profileByUserId);
    assertEquals(profileByUserId.getUser().getEmail(), DEFAULT_TEST_USER_EMAIL);
  }

  @Test
  public void testSearchForProfiles() throws Exception {
    logger.debug("Execute test: testSearchForProfiles()");
    List<Profile> profiles = profileService.searchForProfiles(
        DEFAULT_TEST_BASIC_INFORMATION_FIRSTNAME);
    assertNotNull(profiles);
    assertEquals(profiles.get(0).getBasicInformation().getFirstname(),
        DEFAULT_TEST_BASIC_INFORMATION_FIRSTNAME);
  }

  @Test
  public void testIsFieldContainsSpecialCharactersFalse() {
    logger.debug("Execute test: testIsFieldContainsSpecialCharactersFalse()");
    assertFalse(profileService.isFieldContainsSpecialCharacters(
        DEFAULT_TEST_FIELD_WITHOUT_SPECIAL_CHARACTERS));
  }

  @Test
  public void testIsFieldContainsSpecialCharactersTrue() {
    logger.debug("Execute test: testIsFieldContainsSpecialCharactersTrue()");
    assertTrue(profileService.isFieldContainsSpecialCharacters(
        DEFAULT_TEST_FIELD_WITH_SPECIAL_CHARACTERS));
  }

  @Test
  public void testCountProfiles() {
    logger.debug("Execute test: testCountProfiles()");
    Long count = profileService.countProfiles();
    assertNotNull(count);
  }

}
