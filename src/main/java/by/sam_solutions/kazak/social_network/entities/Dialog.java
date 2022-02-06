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
@Table(name = "dialog")
public class Dialog implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "sender_profile_id", nullable = false)
  private Profile senderProfile;

  @ManyToOne
  @JoinColumn(name = "recipient_profile_id", nullable = false)
  private Profile recipientProfile;

  @Column(name = "time_creation", nullable = false)
  private LocalDateTime timeCreation;

  public Dialog() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Profile getSenderProfile() {
    return senderProfile;
  }

  public void setSenderProfile(Profile senderProfile) {
    this.senderProfile = senderProfile;
  }

  public Profile getRecipientProfile() {
    return recipientProfile;
  }

  public void setRecipientProfile(Profile recipientProfile) {
    this.recipientProfile = recipientProfile;
  }

  public LocalDateTime getTimeCreation() {
    return timeCreation;
  }

  public void setTimeCreation(LocalDateTime timeCreation) {
    this.timeCreation = timeCreation;
  }

}
