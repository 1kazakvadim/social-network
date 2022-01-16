package by.sam_solutions.kazak.social_network.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "text")
  private String text;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "photo_id")
  private Photo photo;

  @Column(name = "like_count")
  private Integer likeCount;

  @Column(name = "time_creation")
  private Timestamp timeCreation;

  public Comment() {
  }

  public Comment(Long id, String text, User user,
      Photo photo, Integer likeCount, Timestamp timeCreation) {
    this.id = id;
    this.text = text;
    this.user = user;
    this.photo = photo;
    this.likeCount = likeCount;
    this.timeCreation = timeCreation;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Photo getPhoto() {
    return photo;
  }

  public void setPhoto(Photo photo) {
    this.photo = photo;
  }

  public Integer getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(Integer likeCount) {
    this.likeCount = likeCount;
  }

  public Timestamp getTimeCreation() {
    return timeCreation;
  }

  public void setTimeCreation(Timestamp timeCreation) {
    this.timeCreation = timeCreation;
  }

}
