package by.sam_solutions.kazak.social_network.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password")
  private String password;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  @Column(name = "locked", nullable = false)
  private boolean isLocked;

  @Column(name = "time_registration", nullable = false)
  private Timestamp timeRegistration;

  @ManyToMany(cascade = {CascadeType.ALL})
  @JoinTable(
      name = "users_to_dialogs",
      joinColumns = {@JoinColumn(name = "user_id")},
      inverseJoinColumns = {@JoinColumn(name = "dialog_id")})
  private Set<Dialog> dialogs = new HashSet<>();

  public User() {}

  public User(
      Long id,
      String email,
      String password,
      Role role,
      boolean isLocked,
      Timestamp timeRegistration) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.role = role;
    this.isLocked = isLocked;
    this.timeRegistration = timeRegistration;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public boolean isLocked() {
    return isLocked;
  }

  public void setLocked(boolean locked) {
    isLocked = locked;
  }

  public Timestamp getTimeRegistration() {
    return timeRegistration;
  }

  public void setTimeRegistration(Timestamp timeRegistration) {
    this.timeRegistration = timeRegistration;
  }

  public Set<Dialog> getProjects() {
    return dialogs;
  }

  public void setProjects(Set<Dialog> projects) {
    this.dialogs = projects;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return isLocked == user.isLocked
        && Objects.equals(id, user.id)
        && Objects.equals(email, user.email)
        && Objects.equals(password, user.password)
        && Objects.equals(role, user.role)
        && Objects.equals(timeRegistration, user.timeRegistration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, role, isLocked, timeRegistration);
  }

}
