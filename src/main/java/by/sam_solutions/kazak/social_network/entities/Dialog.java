package by.sam_solutions.kazak.social_network.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dialog")
public class Dialog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "time_creation")
  private LocalDateTime timeCreation;

  public Dialog() {
  }

  public Dialog(Integer id, String name, LocalDateTime timeCreation) {
    this.id = id;
    this.name = name;
    this.timeCreation = timeCreation;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
    Dialog dialog = (Dialog) o;
    return Objects.equals(id, dialog.id) &&
        Objects.equals(name, dialog.name) &&
        Objects.equals(timeCreation, dialog.timeCreation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, timeCreation);
  }

}
