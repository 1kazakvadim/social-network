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
@Table(name = "dialog")
public class Message implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

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
  private Timestamp timeCreation;

  public Message() {
  }

  public Message(Long id, Dialog dialog, String text, String userFirstname,
      String userLastname, Timestamp timeCreation) {
    this.id = id;
    this.dialog = dialog;
    this.text = text;
    this.userFirstname = userFirstname;
    this.userLastname = userLastname;
    this.timeCreation = timeCreation;
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

  public Timestamp getTimeCreation() {
    return timeCreation;
  }

  public void setTimeCreation(Timestamp timeCreation) {
    this.timeCreation = timeCreation;
  }

}
