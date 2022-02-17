package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import java.util.List;

public interface FriendService {

  void saveOrUpdate(Friend friend);

  Friend getById(Long id);

  List<Friend> getAllByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus);

  Friend getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(Long makeFriendRequestProfileId,
      Long acceptFriendRequestProfileId);

  boolean isFriend(Friend friend);

  boolean hasFriendRequest(Friend friend);

  boolean isNonFriend(Friend friend);

}
