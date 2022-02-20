package by.sam_solutions.kazak.social_network.dao.impl;

import by.sam_solutions.kazak.social_network.dao.AbstractBaseDao;
import by.sam_solutions.kazak.social_network.dao.FriendDao;
import by.sam_solutions.kazak.social_network.entities.Friend;
import by.sam_solutions.kazak.social_network.entities.FriendStatus;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FriendDaoImpl extends AbstractBaseDao<Friend> implements FriendDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void saveOrUpdate(Friend obj) {
    super.saveOrUpdate(obj);
  }

  @Override
  public Friend getById(Long id) {
    return getById(Friend.class, id);
  }

  @Override
  public List<Friend> getAll() {
    return getAll(Friend.class);
  }

  @Override
  public void deleteById(Long id) {
    delete(getById(Friend.class, id));
  }

  @Override
  public List<Friend> getAllByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus) {
    return sessionFactory.getCurrentSession()
        .createQuery(
            "FROM Friend friend WHERE (friend.friendId.makeFriendRequestProfileId = :id "
                + "OR friend.friendId.acceptFriendRequestProfileId = :id) "
                + "AND friend.friendStatus = :friendStatus"
        ).setParameter("id", id)
        .setParameter("friendStatus", friendStatus)
        .list();
  }

  @Override
  public List<Friend> getAllByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus,
      Integer page, Integer size) {
    Query query = sessionFactory.getCurrentSession()
        .createQuery(
            "FROM Friend friend WHERE (friend.friendId.makeFriendRequestProfileId = :id "
                + "OR friend.friendId.acceptFriendRequestProfileId = :id) "
                + "AND friend.friendStatus = :friendStatus"
        ).setParameter("id", id)
        .setParameter("friendStatus", friendStatus);
    query.setFirstResult(page * size);
    query.setMaxResults(size);
    return (List<Friend>) query.list();
  }

  @Override
  public Friend getByMakeRequestProfileIdAndAcceptFriendRequestProfileId(
      Long makeFriendRequestProfileId,
      Long acceptFriendRequestProfileId) {
    return (Friend) sessionFactory.getCurrentSession()
        .createQuery(
            "FROM Friend friend WHERE (friend.friendId.makeFriendRequestProfileId = :makeFriendRequestProfileId "
                + "AND friend.friendId.acceptFriendRequestProfileId = :acceptFriendRequestProfileId) "
                + "OR (friend.friendId.makeFriendRequestProfileId = :acceptFriendRequestProfileId "
                + "AND friend.friendId.acceptFriendRequestProfileId = :makeFriendRequestProfileId)")
        .setParameter("makeFriendRequestProfileId", makeFriendRequestProfileId)
        .setParameter("acceptFriendRequestProfileId", acceptFriendRequestProfileId)
        .uniqueResult();
  }

  @Override
  public Long countByProfileIdAndFriendStatus(Long id, FriendStatus friendStatus) {
    return (Long) sessionFactory.getCurrentSession()
        .createQuery(
            "SELECT count(friend) FROM Friend friend WHERE (friend.friendId.makeFriendRequestProfileId = :id "
                + "OR friend.friendId.acceptFriendRequestProfileId = :id) "
                + "AND friend.friendStatus = :friendStatus"
        ).setParameter("id", id)
        .setParameter("friendStatus", friendStatus)
        .getSingleResult();
  }

}
