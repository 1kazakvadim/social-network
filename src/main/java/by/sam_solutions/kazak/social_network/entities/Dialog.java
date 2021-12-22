package by.sam_solutions.kazak.social_network.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dialog")
public class Dialog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "time_creation")
  private Timestamp timeCreation;

  @ManyToMany(mappedBy = "dialogs")
  private Set<User> users = new HashSet<>();

  public Dialog() {
  }

  public Dialog(Long id, String name, Timestamp timeCreation) {
    this.id = id;
    this.name = name;
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

  public Timestamp getTimeCreation() {
    return timeCreation;
  }

  public void setTimeCreation(Timestamp timeCreation) {
    this.timeCreation = timeCreation;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
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
