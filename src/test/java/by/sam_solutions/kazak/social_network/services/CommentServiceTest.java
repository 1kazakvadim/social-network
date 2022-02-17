package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Comment;
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
public class CommentServiceTest {

  private final Logger logger = LoggerFactory.getLogger(CommentServiceTest.class);

  @Autowired
  private CommentService commentService;

  @Autowired
  private PhotoService photoService;

  @Autowired
  private ProfileService profileService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private BasicInformationService basicInformationService;

  @Autowired
  private UserService userService;

  private Comment comment;
  private Photo photo;
  private Profile profile;
  private BasicInformation basicInformation;
  private User user;

  @BeforeTransaction
  public void addValues() {
    comment = new Comment();
    photo = new Photo();
    profile = new Profile();
    basicInformation = new BasicInformation();
    user = new User();
    user.setEmail("email");
    user.setPassword("password");
    user.setRole(roleService.getById(1L));
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
    photo.setDescription("description");
    photo.setProfile(profile);
    photo.setTimeCreation(LocalDateTime.now());
    photoService.saveOrUpdate(photo);
    comment.setText("test");
    comment.setProfile(profile);
    comment.setPhoto(photo);
    comment.setTimeCreation(LocalDateTime.now());
    commentService.saveOrUpdate(comment);
  }

  @AfterTransaction
  public void removeValues() {
    commentService.deleteById(comment.getId());
    photoService.deleteById(photo.getId());
    profileService.deleteById(profile.getId());
    basicInformationService.deleteById(basicInformation.getId());
    userService.deleteById(user.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");
    comment.setText("updatedText");
    comment.setTimeCreation(LocalDateTime.now().plusHours(1));
    commentService.saveOrUpdate(comment);
    Comment updatedComment = commentService.getById(comment.getId());
    assertEquals(updatedComment.getId(), comment.getId());
    assertEquals(updatedComment.getText(), comment.getText());
    assertEquals(updatedComment.getProfile(), comment.getProfile());
    assertEquals(updatedComment.getPhoto(), comment.getPhoto());
    assertEquals(updatedComment.getTimeCreation(), comment.getTimeCreation());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Comment commentById = commentService.getById(comment.getId());
    assertEquals(comment.getId(), commentById.getId());
    assertEquals(comment.getText(), commentById.getText());
    assertEquals(comment.getProfile(), commentById.getProfile());
    assertEquals(comment.getPhoto(), commentById.getPhoto());
    assertEquals(comment.getTimeCreation(), commentById.getTimeCreation());
  }

  @Test
  public void testGetAllByPhotoId() {
    logger.debug("Execute test: testGetAllByPhotoId()");
    List<Comment> comments = commentService.getAllByPhotoId(comment.getPhoto().getId());
    assertNotNull(comments);
    assertEquals(1, comments.size());
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    commentService.deleteById(comment.getId());
    assertNull(commentService.getById(comment.getId()));
  }

}
