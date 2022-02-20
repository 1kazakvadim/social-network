package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Photo;
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
public class PhotoServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(PhotoServiceTest.class);

  private static final String DEFAULT_TEST_PHOTO_NAME = "testName";
  private static final String DEFAULT_TEST_PHOTO_DESCRIPTION = "testDescription";

  @Autowired
  private PhotoService photoService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private ProfileService profileService;

  @Autowired
  private BasicInformationService basicInformationService;

  @Autowired
  private UserService userService;

  private Photo photo;
  private Profile profile;
  private BasicInformation basicInformation;
  private User user;
  private Role role;

  @BeforeTransaction
  public void addValues() {
    photo = new Photo();
    profile = new Profile();
    basicInformation = new BasicInformation();
    role = new Role();
    user = new User();
    role.setName("name");
    roleService.saveOrUpdate(role);
    user.setEmail("email");
    user.setPassword("password");
    user.setRole(role);
    user.setLocked(false);
    basicInformation.setFirstname("firstname");
    basicInformation.setLastname("lastname");
    basicInformation.setGender("MALE");
    basicInformation.setBirthday(LocalDate.now().minusYears(1));
    profile.setUser(user);
    profile.setBasicInformation(basicInformation);
    profile.setTimeRegistration(LocalDateTime.now());
    profile.setUpdateTime(LocalDateTime.now());
    profileService.saveOrUpdate(profile);
    photo.setName(DEFAULT_TEST_PHOTO_NAME);
    photo.setProfile(profile);
    photo.setDescription(DEFAULT_TEST_PHOTO_DESCRIPTION);
    photo.setTimeCreation(LocalDateTime.now());
    photoService.saveOrUpdate(photo);
  }

  @AfterTransaction
  public void removeValues() {
    photoService.deleteById(photo.getId());
    profileService.deleteById(profile.getId());
    basicInformationService.deleteById(basicInformation.getId());
    userService.deleteById(user.getId());
    roleService.deleteById(role.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");

    final String updatedTestName = "updatedTestName";
    final String updatedTestDescription = "updatedTestDescription";

    Photo photo = new Photo();
    photo.setId(this.profile.getId());
    photo.setName(updatedTestName);
    photo.setDescription(updatedTestDescription);
    photoService.saveOrUpdate(photo);
    Photo updatedPhoto = photoService.getById(photo.getId());
    assertNotNull(updatedPhoto);
    assertEquals(updatedTestName, updatedPhoto.getName());
    assertEquals(updatedTestDescription, updatedPhoto.getDescription());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Photo photoById = photoService.getById(photo.getId());
    assertNotNull(photoById);
    assertEquals(photoById.getId(), photo.getId());
    assertEquals(photoById.getName(), DEFAULT_TEST_PHOTO_NAME);
    assertEquals(photoById.getDescription(), DEFAULT_TEST_PHOTO_DESCRIPTION);
    assertEquals(photoById.getTimeCreation(), photo.getTimeCreation());
  }

  @Test
  public void testGetAll() {
    logger.debug("Execute test: testGetAll()");
    List<Photo> photos = photoService.getAll();
    assertNotNull(photos);
    assertEquals(1, photos.size());
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    photoService.deleteById(photo.getId());
    assertNull(photoService.getById(photo.getId()));
  }

  @Test
  public void testGetAllByProfileId() {
    logger.debug("Execute test: testGetAllByProfileId()");
    List<Photo> photos = photoService.getAllByProfileId(profile.getId());
    assertNotNull(photos);
    assertEquals(1, photos.size());
  }

  @Test
  public void testCountByProfileId() {
    logger.debug("Execute test: testCountByProfileId()");
    Long count = photoService.countByProfileId(profile.getId());
    assertNotNull(count);
  }

}
