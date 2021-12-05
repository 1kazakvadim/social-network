package by.sam_solutions.kazak.social_network.dto;

import by.sam_solutions.kazak.social_network.entities.Role;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserDTO {

  private Long id;
  private String email;
  private String password;
  private Role role;
  private boolean isLocked;
  private LocalDateTime timeRegistration;

  public UserDTO() {
  }

  public UserDTO(Long id, String email, String password,
      Role role, boolean isLocked, LocalDateTime timeRegistration) {
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

  public LocalDateTime getTimeRegistration() {
    return timeRegistration;
  }

  public void setTimeRegistration(LocalDateTime timeRegistration) {
    this.timeRegistration = timeRegistration;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDTO userDTO = (UserDTO) o;
    return isLocked == userDTO.isLocked &&
        Objects.equals(id, userDTO.id) &&
        Objects.equals(email, userDTO.email) &&
        Objects.equals(password, userDTO.password) &&
        Objects.equals(role, userDTO.role) &&
        Objects.equals(timeRegistration, userDTO.timeRegistration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, email, password, role, isLocked, timeRegistration);
  }

}
