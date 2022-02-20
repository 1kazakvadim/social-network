package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import java.util.List;

public interface FriendDao extends IAbstractBaseDao<Friend> {

  List<Friend> getAllByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus);

  List<Friend> getAllByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus, Integer page,
      Integer size);

  Friend getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(Long makeFriendRequestProfileId,
      Long acceptFriendRequestProfileId);

  Long countByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus);

}
