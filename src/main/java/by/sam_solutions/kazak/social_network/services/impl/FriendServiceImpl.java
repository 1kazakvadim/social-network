package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.FriendDao;
import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import by.sam_solutions.kazak.social_network.services.FriendService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {

  private final Logger logger = LoggerFactory.getLogger(FriendServiceImpl.class);

  @Autowired
  private FriendDao friendDao;

  @Override
  public void saveOrUpdate(Friend friend) {
    logger.debug("saveOrUpdate({})", friend);
    friendDao.saveOrUpdate(friend);
  }

  @Override
  public Friend getById(Long id) {
    logger.debug("get friend by id = {}", id);
    return friendDao.getById(id);
  }

  @Override
  public List<Friend> getAll() {
    logger.debug("get all friends");
    return friendDao.getAll();
  }

  @Override
  public List<Friend> getAllByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus) {
    logger.debug("get friend by profile id = {} and by friend status = {}", id, friendStatus);
    return friendDao.getAllByProfileIdAndFriendStatus(id, friendStatus);
  }

  @Override
  public Friend getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
      Long makeFriendRequestProfileId, Long acceptFriendRequestProfileId) {
    return friendDao.getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
        makeFriendRequestProfileId, acceptFriendRequestProfileId);
  }

  @Override
  public boolean isFriend(Friend friend) {
    return friend.getFriendStatus() == FriendStatus.IN_FRIEND;
  }

  @Override
  public boolean hasFriendRequest(Friend friend) {
    return friend.getFriendStatus() == FriendStatus.FRIEND_REQUEST;
  }

  @Override
  public boolean isNonFriend(Friend friend) {
    return friend.getFriendStatus() == FriendStatus.NON_FRIEND;
  }

  @Override
  public void deleteById(Long id) {
    logger.debug("delete friend with id = {}", id);
    friendDao.deleteById(id);
  }

}
