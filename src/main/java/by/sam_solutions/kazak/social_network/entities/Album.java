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
@Table(name = "album")
public class Album implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "time_creation")
  private Timestamp timeCreation;

  public Album() {
  }

  public Album(Long id, String name, User user, Timestamp timeCreation) {
    this.id = id;
    this.name = name;
    this.user = user;
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Timestamp getTimeCreation() {
    return timeCreation;
  }

  public void setTimeCreation(Timestamp timeCreation) {
    this.timeCreation = timeCreation;
  }

}
