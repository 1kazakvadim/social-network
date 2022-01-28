package by.sam_solutions.kazak.social_network.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FriendId implements Serializable {

  @Column(name = "profile_make_friend_request_id", nullable = false)
  private Long makeFriendRequestProfileId;

  @Column(name = "profile_accept_friend_request_id", nullable = false)
  private Long acceptFriendRequestProfileId;

  public FriendId() {
  }

  public FriendId(Long makeFriendRequestProfileId, Long acceptFriendRequestProfileId) {
    this.makeFriendRequestProfileId = makeFriendRequestProfileId;
    this.acceptFriendRequestProfileId = acceptFriendRequestProfileId;
  }

  public Long getMakeFriendRequestProfileId() {
    return makeFriendRequestProfileId;
  }

  public void setMakeFriendRequestProfileId(Long makeFriendRequestProfileId) {
    this.makeFriendRequestProfileId = makeFriendRequestProfileId;
  }

  public Long getAcceptFriendRequestProfileId() {
    return acceptFriendRequestProfileId;
  }

  public void setAcceptFriendRequestProfileId(Long acceptFriendRequestProfileId) {
    this.acceptFriendRequestProfileId = acceptFriendRequestProfileId;
  }

}
