package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import java.util.List;

public interface FriendService {

  void saveOrUpdate(Friend friend);

  Friend getById(Long id);

  List<Friend> getAllByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus);

  List<Friend> getAllByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus, Integer page,
      Integer size);

  Friend getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(Long makeFriendRequestProfileId,
      Long acceptFriendRequestProfileId);

  boolean isFriend(Friend friend);

  boolean hasFriendRequest(Friend friend);

  boolean isNonFriend(Friend friend);

  Long countByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus);

}
