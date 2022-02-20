package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.facades.FriendFacade;
import by.sam_solutions.kazak.social_network.services.FriendService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FriendFacadeImpl implements FriendFacade {

  @Autowired
  private FriendService friendService;

  @Override
  public void saveOrUpdate(Friend friend) {
    friendService.saveOrUpdate(friend);
  }

  @Override
  public Friend getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
      Long makeFriendRequestProfileId, Long acceptFriendRequestProfileId) {
    return friendService.getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
        makeFriendRequestProfileId,
        acceptFriendRequestProfileId);
  }

  @Override
  public Friend createFriendWithFriendStatus(Profile makeFriendRequestProfile,
      Profile acceptFriendRequestProfile, FriendStatus friendStatus) {
    Friend friend = new Friend(makeFriendRequestProfile, acceptFriendRequestProfile,
        friendStatus, LocalDateTime.now());
    friendService.saveOrUpdate(friend);
    return friend;
  }

  @Override
  public void changeFriendStatus(Friend friend, FriendStatus friendStatus) {
    friend.setFriendStatus(friendStatus);
    friendService.saveOrUpdate(friend);
  }

  @Override
  public boolean isFriend(Friend friend) {
    return friendService.isFriend(friend);
  }

  @Override
  public boolean hasFriendRequest(Friend friend) {
    return friendService.hasFriendRequest(friend);
  }

  @Override
  public boolean isNonFriend(Friend friend) {
    return friendService.isNonFriend(friend);
  }

  @Override
  public Long countByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus) {
    return friendService.countByProfileIdAndFriendStatus(id, friendStatus);
  }

}
