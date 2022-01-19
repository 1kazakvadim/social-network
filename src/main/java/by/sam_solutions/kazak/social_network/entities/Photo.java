package by.sam_solutions.kazak.social_network.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "photo")
public class Photo implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @ManyToOne
  @JoinColumn(name = "profile_id", nullable = false)
  private Profile profile;

  @Column(name = "description")
  private String description;

  @Column(name = "like_count")
  private Integer likeCount;

  @Column(name = "time_creation", nullable = false)
  private LocalDateTime timeCreation;

  public Photo() {
  }

  public Photo(Long id, String name, Profile profile, String description, Integer likeCount,
      LocalDateTime timeCreation) {
    this.id = id;
    this.name = name;
    this.profile = profile;
    this.description = description;
    this.likeCount = likeCount;
    this.timeCreation = timeCreation;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

}
