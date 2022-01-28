package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import by.sam_solutions.kazak.social_network.entities.Profile;

public interface FriendFacade {

  void saveOrUpdate(Friend friend);

  Friend getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(Long makeFriendRequestProfileId,
      Long acceptFriendRequestProfileId);

  Friend createFriendWithFriendStatus(Profile makeFriendRequestProfile,
      Profile acceptFriendRequestProfile, FriendStatus friendStatus);

  void changeFriendStatus(Friend friend, FriendStatus friendStatus);

  boolean isFriend(Friend friend);

  boolean hasFriendRequest(Friend friend);

  boolean isNonFriend(Friend friend);

}
