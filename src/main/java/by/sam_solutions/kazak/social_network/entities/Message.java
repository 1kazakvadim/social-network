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
@Table(name = "dialog")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "dialog_id")
  private Dialog dialog;

  @Column(name = "text")
  private String text;

  @Column(name = "user_firstname")
  private String userFirstname;

  @Column(name = "user_lastname")
  private String userLastname;

  @Column(name = "time_creation")
  private LocalDateTime timeCreation;

  public Message() {
  }

  public Message(Integer id, Dialog dialog, String text, String userFirstname,
      String userLastname, LocalDateTime timeCreation) {
    this.id = id;
    this.dialog = dialog;
    this.text = text;
    this.userFirstname = userFirstname;
    this.userLastname = userLastname;
    this.timeCreation = timeCreation;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Dialog getDialog() {
    return dialog;
  }

  public void setDialog(Dialog dialog) {
    this.dialog = dialog;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getUserFirstname() {
    return userFirstname;
  }

  public void setUserFirstname(String userFirstname) {
    this.userFirstname = userFirstname;
  }

  public String getUserLastname() {
    return userLastname;
  }

  public void setUserLastname(String userLastname) {
    this.userLastname = userLastname;
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
    Message message = (Message) o;
    return Objects.equals(id, message.id) &&
        Objects.equals(dialog, message.dialog) &&
        Objects.equals(text, message.text) &&
        Objects.equals(userFirstname, message.userFirstname) &&
        Objects.equals(userLastname, message.userLastname) &&
        Objects.equals(timeCreation, message.timeCreation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dialog, text, userFirstname, userLastname, timeCreation);
  }

}
