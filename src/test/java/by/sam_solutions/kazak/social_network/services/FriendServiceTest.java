package by.sam_solutions.kazak.social_network.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import by.sam_solutions.kazak.social_network.config.TestAppContextConfig;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Dialog;
import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
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
public class FriendServiceTest {

  private static final Logger logger = LoggerFactory.getLogger(FriendServiceTest.class);

  private static final FriendStatus DEFAULT_TEST_FRIEND_STATUS = FriendStatus.IN_FRIEND;
  private static final LocalDateTime DEFAULT_TEST_FRIEND_TIME_CREATION = LocalDateTime.now();

  @Autowired
  private FriendService friendService;

  @Autowired
  private ProfileService profileService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private BasicInformationService basicInformationService;

  @Autowired
  private UserService userService;

  private Friend friend;
  private Profile makeRequestProfile;
  private Profile acceptRequestProfile;
  private BasicInformation makeRequestBasicInformation;
  private BasicInformation acceptRequestBasicInformation;
  private User makeRequestUser;
  private User acceptRequestUser;
  private Role role;

  @BeforeTransaction
  public void addValues() {
    makeRequestProfile = new Profile();
    acceptRequestProfile = new Profile();
    makeRequestBasicInformation = new BasicInformation();
    acceptRequestBasicInformation = new BasicInformation();
    role = new Role();
    makeRequestUser = new User();
    acceptRequestUser = new User();
    role.setName("name");
    roleService.saveOrUpdate(role);
    makeRequestUser.setEmail("makeRequestEmail");
    makeRequestUser.setPassword("makeRequestPassword");
    makeRequestUser.setRole(role);
    makeRequestUser.setLocked(false);
    acceptRequestUser.setEmail("acceptRequestEmail");
    acceptRequestUser.setPassword("acceptRequestPassword");
    acceptRequestUser.setRole(role);
    acceptRequestUser.setLocked(false);
    makeRequestBasicInformation.setFirstname("makeRequestFirstname");
    makeRequestBasicInformation.setLastname("makeRequestLastname");
    makeRequestBasicInformation.setGender("MALE");
    makeRequestBasicInformation.setBirthday(LocalDate.now().minusYears(1));
    acceptRequestBasicInformation.setFirstname("acceptRequestFirstname");
    acceptRequestBasicInformation.setLastname("acceptRequestLastname");
    acceptRequestBasicInformation.setGender("FEMALE");
    acceptRequestBasicInformation.setBirthday(LocalDate.now().minusYears(1));
    makeRequestProfile.setUser(makeRequestUser);
    makeRequestProfile.setBasicInformation(makeRequestBasicInformation);
    makeRequestProfile.setTimeRegistration(LocalDateTime.now());
    makeRequestProfile.setUpdateTime(LocalDateTime.now());
    acceptRequestProfile.setUser(acceptRequestUser);
    acceptRequestProfile.setBasicInformation(acceptRequestBasicInformation);
    acceptRequestProfile.setTimeRegistration(LocalDateTime.now());
    acceptRequestProfile.setUpdateTime(LocalDateTime.now());
    profileService.saveOrUpdate(makeRequestProfile);
    profileService.saveOrUpdate(acceptRequestProfile);
    friend = new Friend(makeRequestProfile, acceptRequestProfile, DEFAULT_TEST_FRIEND_STATUS,
        DEFAULT_TEST_FRIEND_TIME_CREATION);
    friendService.saveOrUpdate(friend);
  }

  @AfterTransaction
  public void removeValues() {
    profileService.deleteById(makeRequestProfile.getId());
    profileService.deleteById(acceptRequestProfile.getId());
    basicInformationService.deleteById(makeRequestBasicInformation.getId());
    basicInformationService.deleteById(acceptRequestBasicInformation.getId());
    userService.deleteById(makeRequestUser.getId());
    userService.deleteById(acceptRequestUser.getId());
    roleService.deleteById(role.getId());
  }

  @Test
  public void testSaveOrUpdate() {
    logger.debug("Execute test: testSaveOrUpdate()");

    final FriendStatus updatedFriendStatus = FriendStatus.NON_FRIEND;
    final LocalDateTime updatedTimeCreation = LocalDateTime.now().plusDays(1);

    Friend friend = new Friend();
    friend.setFriendId(this.friend.getFriendId());
    friend.setFriendStatus(updatedFriendStatus);
    friend.setTimeCreation(updatedTimeCreation);
    friendService.saveOrUpdate(friend);
    Friend updatedFriend = friendService.getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
        makeRequestProfile.getId(), acceptRequestProfile.getId());
    assertNotNull(updatedFriend);
    assertEquals(updatedFriendStatus, updatedFriend.getFriendStatus());
    assertEquals(updatedTimeCreation, updatedFriend.getTimeCreation());
  }

  @Test
  public void testGetAllByProfileIdAndFriendStatus() {
    logger.debug("Execute test: testGetAllByProfileIdAndFriendStatus()");
    List<Friend> friends = friendService.getAllByProfileIdAndFriendStatus(
        makeRequestProfile.getId(), DEFAULT_TEST_FRIEND_STATUS);
    assertNotNull(friends);
    assertEquals(1, friends.size());
  }

  @Test
  public void testGetByMakeRequestProfileIdAndAcceptFriendRequestProfileId() {
    logger.debug("Execute test: testGetByMakeRequestProfileIdAndAcceptFriendRequestProfileId()");
    Friend friend = friendService.getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
        makeRequestProfile.getId(), acceptRequestProfile.getId());
    assertNotNull(friend);
    assertEquals(friend.getMakeRequestProfile().getId(), makeRequestProfile.getId());
    assertEquals(friend.getAcceptRequestProfile().getId(), acceptRequestProfile.getId());
  }

  @Test
  public void testIsFriendTrue() {
    logger.debug("Execute test: testIsFriendTrue()");
    assertTrue(friendService.isFriend(friend));
  }

  @Test
  @Rollback
  public void testIsFriendFalse() {
    logger.debug("Execute test: testIsFriendFalse()");
    friend.setFriendStatus(FriendStatus.NON_FRIEND);
    friendService.saveOrUpdate(friend);
    assertFalse(friendService.isFriend(friend));
  }

  @Test
  @Rollback
  public void testHasFriendRequestTrue() {
    logger.debug("Execute test: testHasFriendRequestTrue()");
    friend.setFriendStatus(FriendStatus.FRIEND_REQUEST);
    friendService.saveOrUpdate(friend);
    assertTrue(friendService.hasFriendRequest(friend));
  }

  @Test
  public void testHasFriendRequestFalse() {
    logger.debug("Execute test: testHasFriendRequestFalse()");
    assertFalse(friendService.hasFriendRequest(friend));
  }

  @Test
  @Rollback
  public void testIsNonFriendTrue() {
    logger.debug("Execute test: testIsNonFriendTrue()");
    friend.setFriendStatus(FriendStatus.NON_FRIEND);
    friendService.saveOrUpdate(friend);
    assertTrue(friendService.isNonFriend(friend));
  }

  @Test
  public void testIsNonFriendFalse() {
    logger.debug("Execute test: testIsNonFriendFalse()");
    assertFalse(friendService.isNonFriend(friend));
  }


}
