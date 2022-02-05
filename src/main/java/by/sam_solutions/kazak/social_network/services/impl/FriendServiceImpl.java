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
public class FriendServiceImpl implements FriendService {

  private final Logger logger = LoggerFactory.getLogger(FriendServiceImpl.class);

  @Autowired
  private FriendDao friendDao;

  @Override
  @Transactional
  public void saveOrUpdate(Friend friend) {
    logger.debug("saveOrUpdate({})", friend);
    friendDao.saveOrUpdate(friend);
  }

  @Override
  @Transactional(readOnly = true)
  public Friend getById(Long id) {
    logger.debug("get friend by id = {}", id);
    return friendDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Friend> getAll() {
    logger.debug("get all friends");
    return friendDao.getAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Friend> getAllByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus) {
    logger.debug("get friend by profile id = {} and by friend status = {}", id, friendStatus);
    return friendDao.getAllByProfileIdAndFriendStatus(id, friendStatus);
  }

  @Override
  @Transactional(readOnly = true)
  public Friend getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
      Long makeFriendRequestProfileId, Long acceptFriendRequestProfileId) {
    logger.debug(
        "get friend by makeFriendRequestProfileId and acceptFriendRequestProfileId = {}, {}",
        makeFriendRequestProfileId, acceptFriendRequestProfileId);
    return friendDao.getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
        makeFriendRequestProfileId, acceptFriendRequestProfileId);
  }

  @Override
  public boolean isFriend(Friend friend) {
    logger.debug("is friend = {}", friend.getFriendId());
    return friend.getFriendStatus() == FriendStatus.IN_FRIEND;
  }

  @Override
  public boolean hasFriendRequest(Friend friend) {
    logger.debug("has friend request = {}", friend.getFriendId());
    return friend.getFriendStatus() == FriendStatus.FRIEND_REQUEST;
  }

  @Override
  public boolean isNonFriend(Friend friend) {
    logger.debug("is non request = {}", friend.getFriendId());
    return friend.getFriendStatus() == FriendStatus.NON_FRIEND;
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete friend with id = {}", id);
    friendDao.deleteById(id);
  }

}
