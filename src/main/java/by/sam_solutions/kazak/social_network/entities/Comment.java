package by.sam_solutions.kazak.social_network.entities;

import java.time.LocalDateTime;
import java.util.Objects;
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
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

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
  private LocalDateTime timeCreation;

  public Comment() {
  }

  public Comment(Integer id, String text, User user,
      Photo photo, Integer likeCount, LocalDateTime timeCreation) {
    this.id = id;
    this.text = text;
    this.user = user;
    this.photo = photo;
    this.likeCount = likeCount;
    this.timeCreation = timeCreation;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public LocalDateTime getTimeCreation() {
    return timeCreation;
  }

  public void setTimeCreation(LocalDateTime timeCreation) {
    this.timeCreation = timeCreation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Comment comment = (Comment) o;
    return Objects.equals(id, comment.id) &&
        Objects.equals(text, comment.text) &&
        Objects.equals(user, comment.user) &&
        Objects.equals(photo, comment.photo) &&
        Objects.equals(likeCount, comment.likeCount) &&
        Objects.equals(timeCreation, comment.timeCreation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, text, user, photo, likeCount, timeCreation);
  }

}
