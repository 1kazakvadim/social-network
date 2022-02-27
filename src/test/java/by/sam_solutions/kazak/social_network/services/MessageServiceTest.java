package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Dialog;
import by.sam_solutions.kazak.social_network.entities.Message;
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
public class MessageServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(MessageServiceTest.class);

  private static final String DEFAULT_TEST_MESSAGE_TEXT = "testText";

  @Autowired
  private MessageService messageService;

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

  private Message message;
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
    message = new Message();
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
    dialog.setTimeCreation(LocalDateTime.now());
    dialogService.saveOrUpdate(dialog);
    message.setMessageSender(senderProfile);
    message.setMessageText(DEFAULT_TEST_MESSAGE_TEXT);
    message.setDialog(dialog);
    message.setTimeCreation(LocalDateTime.now());
    messageService.saveOrUpdate(message);
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

    final String updatedTestText = "updatedTestText";

    Message message = new Message();
    message.setId(this.message.getId());
    message.setMessageText(updatedTestText);
    messageService.saveOrUpdate(message);
    Message updatedMessage = messageService.getById(message.getId());
    assertNotNull(updatedMessage);
    assertEquals(updatedTestText, updatedMessage.getMessageText());
  }

  @Test
  public void testGetById() {
    logger.debug("Execute test: testGetById()");
    Message messageById = messageService.getById(message.getId());
    assertNotNull(messageById);
    assertEquals(messageById.getId(), message.getId());
    assertEquals(messageById.getMessageText(), DEFAULT_TEST_MESSAGE_TEXT);
  }

  @Test
  public void testGetAllByDialogId() {
    logger.debug("Execute test: testGetAllByDialogId()");
    List<Message> messages = messageService.getAllByDialogId(dialog.getId());
    assertNotNull(messages);
    assertEquals(1, messages.size());
  }

  @Test
  @Rollback
  public void testDeleteById() {
    logger.debug("Execute test: testDeleteById()");
    messageService.deleteById(message.getId());
    assertNull(messageService.getById(message.getId()));
  }

}
