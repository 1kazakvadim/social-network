package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Dialog;
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
public class DialogServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(DialogServiceTest.class);

  private static final LocalDateTime DEFAULT_TEST_DIALOG_TIME_CREATION = LocalDateTime.now();

  @Autowired
  private ProfileService profileService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private BasicInformationService basicInformationService;

  @Autowired
  private UserService userService;

  @Autowired
  private DialogService dialogService;

  private Dialog dialog;
  private Profile senderProfile;
  private Profile recipientProfile;
  private BasicInformation senderBasicInformation;
  private BasicInformation recipientBasicInformation;
  private User senderUser;
  private User recipientUser;
  private Role role;

  @BeforeTransaction
  public void addValues() {
    dialog = new Dialog();
    senderProfile = new Profile();
    recipientProfile = new Profile();
    senderBasicInformation = new BasicInformation();
    recipientBasicInformation = new BasicInformation();
    role = new Role();
    senderUser = new User();
    recipientUser = new User();
    role.setName("name");
    roleService.saveOrUpdate(role);
    senderUser.setEmail("senderEmail");
    senderUser.setPassword("senderPassword");
    senderUser.setRole(role);
    senderUser.setLocked(false);
    recipientUser.setEmail("recipientEmail");
    recipientUser.setPassword("recipientPassword");
    recipientUser.setRole(role);
    recipientUser.setLocked(false);
    senderBasicInformation.setFirstname("senderFirstname");
    senderBasicInformation.setLastname("senderLastname");
    senderBasicInformation.setGender("MALE");
    senderBasicInformation.setBirthday(LocalDate.now().minusYears(1));
    recipientBasicInformation.setFirstname("recipientFirstname");
    recipientBasicInformation.setLastname("recipientLastname");
    recipientBasicInformation.setGender("FEMALE");
    recipientBasicInformation.setBirthday(LocalDate.now().minusYears(1));
    senderProfile.setUser(senderUser);
    senderProfile.setBasicInformation(senderBasicInformation);
    senderProfile.setTimeRegistration(LocalDateTime.now());
    senderProfile.setUpdateTime(LocalDateTime.now());
    recipientProfile.setUser(recipientUser);
    recipientProfile.setBasicInformation(recipientBasicInformation);
    recipientProfile.setTimeRegistration(LocalDateTime.now());
    recipientProfile.setUpdateTime(LocalDateTime.now());
    profileService.saveOrUpdate(senderProfile);
    profileService.saveOrUpdate(recipientProfile);
    dialog.setSenderProfile(senderProfile);
    dialog.setRecipientProfile(recipientProfile);
    dialog.setTimeCreation(DEFAULT_TEST_DIALOG_TIME_CREATION);
    dialogService.saveOrUpdate(dialog);
  }

  @AfterTransaction
  public void removeValues() {
    dialogService.deleteById(dialog.getId());
    profileService.deleteById(senderProfile.getId());
    profileService.deleteById(recipientProfile.getId());
    basicInformationService.deleteById(senderBasicInformation.getId());
    basicInformationService.deleteById(recipientBasicInformation.getId());
    userService.deleteById(senderUser.getId());
    userService.deleteById(recipientUser.getId());
    roleService.deleteById(role.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");

    final LocalDateTime updatedTestTimeCreation = LocalDateTime.now().plusDays(1);

    Dialog dialog = new Dialog();
    dialog.setId(this.dialog.getId());
    dialog.setTimeCreation(updatedTestTimeCreation);
    dialogService.saveOrUpdate(dialog);
    Dialog updatedDialog = dialogService.getById(dialog.getId());
    assertNotNull(updatedDialog);
    assertEquals(updatedTestTimeCreation, updatedDialog.getTimeCreation());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Dialog dialogById = dialogService.getById(dialog.getId());
    assertNotNull(dialogById);
    assertEquals(dialogById.getId(), dialog.getId());
    assertEquals(dialogById.getTimeCreation(), DEFAULT_TEST_DIALOG_TIME_CREATION);
  }

  @Test
  public void getBySenderProfileIdAndRecipientProfileId() {
    logger.debug("Execute test: getBySenderProfileIdAndRecipientProfileId()");
    Dialog dialogBySenderProfileIdAndRecipientProfileId = dialogService.getBySenderProfileIdAndRecipientProfileId(
        senderProfile.getId(), recipientProfile.getId());
    assertNotNull(dialogBySenderProfileIdAndRecipientProfileId);
    assertEquals(dialogBySenderProfileIdAndRecipientProfileId.getId(), dialog.getId());
    assertEquals(dialogBySenderProfileIdAndRecipientProfileId.getSenderProfile().getId(),
        senderProfile.getId());
    assertEquals(dialogBySenderProfileIdAndRecipientProfileId.getRecipientProfile().getId(),
        recipientProfile.getId());
    assertEquals(dialogBySenderProfileIdAndRecipientProfileId.getTimeCreation(),
        DEFAULT_TEST_DIALOG_TIME_CREATION);
  }

  @Test
  public void testGetAllByProfileId() {
    logger.debug("Execute test: testGetAllByProfileId()");
    List<Dialog> dialogs = dialogService.getAllByProfileId(recipientProfile.getId());
    assertNotNull(dialogs);
    assertEquals(1, dialogs.size());
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    dialogService.deleteById(dialog.getId());
    assertNull(dialogService.getById(dialog.getId()));
  }

}
