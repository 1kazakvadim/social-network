package by.sam_solutions.kazak.social_network.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "friend")
public class Friend implements Serializable {

  @EmbeddedId
  private FriendId friendId;

  @ManyToOne
  @MapsId("makeFriendRequestProfileId")
  @JoinColumn(name = "profile_make_friend_request_id")
  private Profile makeRequestProfile;

  @ManyToOne
  @MapsId("acceptFriendRequestProfileId")
  @JoinColumn(name = "profile_accept_friend_request_id")
  private Profile acceptRequestProfile;

  @Enumerated(EnumType.STRING)
  @Column(name = "friend_status", nullable = false)
  private FriendStatus friendStatus;

  @Column(name = "time_creation", nullable = false)
  private LocalDateTime timeCreation;

  public Friend() {
  }

  public Friend(Profile makeRequestProfile,
      Profile acceptRequestProfile,
      FriendStatus friendStatus, LocalDateTime timeCreation) {
    this.friendId = new FriendId(makeRequestProfile.getId(), acceptRequestProfile.getId());
    this.makeRequestProfile = makeRequestProfile;
    this.acceptRequestProfile = acceptRequestProfile;
    this.friendStatus = friendStatus;
    this.timeCreation = timeCreation;
  }

  public FriendId getFriendId() {
    return friendId;
  }

  public void setFriendId(FriendId friendId) {
    this.friendId = friendId;
  }

  public Profile getMakeRequestProfile() {
    return makeRequestProfile;
  }

  public void setMakeRequestProfile(
      Profile makeRequestProfile) {
    this.makeRequestProfile = makeRequestProfile;
  }

  public Profile getAcceptRequestProfile() {
    return acceptRequestProfile;
  }

  public void setAcceptRequestProfile(
      Profile acceptRequestProfile) {
    this.acceptRequestProfile = acceptRequestProfile;
  }

  public FriendStatus getFriendStatus() {
    return friendStatus;
  }

  public void setFriendStatus(FriendStatus friendStatus) {
    this.friendStatus = friendStatus;
  }

  public LocalDateTime getTimeCreation() {
    return timeCreation;
  }

  public void setTimeCreation(LocalDateTime timeCreation) {
    this.timeCreation = timeCreation;
  }

}
