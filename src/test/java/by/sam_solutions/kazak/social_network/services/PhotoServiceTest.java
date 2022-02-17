package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Photo;
import by.sam_solutions.kazak.social_network.entities.Profile;
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

  private final Logger logger = LoggerFactory.getLogger(PhotoServiceTest.class);

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

  @BeforeTransaction
  public void addValues() {
    photo = new Photo();
    profile = new Profile();
    basicInformation = new BasicInformation();
    user = new User();
    user.setEmail("email");
    user.setPassword("password");
    user.setRole(roleService.findByName("USER"));
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
    photo.setName("name");
    photo.setProfile(profile);
    photo.setDescription("description");
    photo.setTimeCreation(LocalDateTime.now());
    photoService.saveOrUpdate(photo);
  }

  @AfterTransaction
  public void removeValues() {
    photoService.deleteById(photo.getId());
    profileService.deleteById(profile.getId());
    basicInformationService.deleteById(basicInformation.getId());
    userService.deleteById(user.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");
    photo.setName("updatedName");
    photo.setDescription("updatedDescription");
    photo.setTimeCreation(LocalDateTime.now().plusHours(1));
    photoService.saveOrUpdate(photo);
    Photo updatedPhoto = photoService.getById(photo.getId());
    assertEquals(updatedPhoto.getId(), photo.getId());
    assertEquals(updatedPhoto.getName(), photo.getName());
    assertEquals(updatedPhoto.getProfile(), photo.getProfile());
    assertEquals(updatedPhoto.getDescription(), photo.getDescription());
    assertEquals(updatedPhoto.getTimeCreation(), photo.getTimeCreation());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Photo photoById = photoService.getById(photo.getId());
    assertEquals(photoById.getId(), photo.getId());
    assertEquals(photoById.getName(), photo.getName());
    assertEquals(photoById.getDescription(), photo.getDescription());
    assertEquals(photoById.getTimeCreation(), photo.getTimeCreation());
  }

  @Test
  public void testGetAll() {
    logger.debug("Execute test: it_should_get_all_photos()");
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

}
