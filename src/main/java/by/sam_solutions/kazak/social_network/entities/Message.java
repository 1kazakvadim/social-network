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
@Table(name = "Message")
public class Message implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "dialog_id", nullable = false)
  private Dialog dialog;

  @Column(name = "message_text")
  private String messageText;

  @ManyToOne
  @JoinColumn(name = "message_sender", nullable = false)
  private Profile messageSender;

  @Column(name = "time_creation", nullable = false)
  private LocalDateTime timeCreation;


  public Message() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Dialog getDialog() {
    return dialog;
  }

  public void setDialog(Dialog dialog) {
    this.dialog = dialog;
  }

  public String getMessageText() {
    return messageText;
  }

  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }

  public Profile getMessageSender() {
    return messageSender;
  }

  public void setMessageSender(Profile messageSender) {
    this.messageSender = messageSender;
  }

  public LocalDateTime getTimeCreation() {
    return timeCreation;
  }

  public void setTimeCreation(LocalDateTime timeCreation) {
    this.timeCreation = timeCreation;
  }

}
