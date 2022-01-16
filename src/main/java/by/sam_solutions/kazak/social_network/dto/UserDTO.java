package by.sam_solutions.kazak.social_network.dto;

import by.sam_solutions.kazak.social_network.entities.Role;

public class UserDTO {

  private Long id;
  private String email;
  private String password;
  private Role role;
  private boolean isLocked;

  public UserDTO() {
  }

  public UserDTO(Long id, String email, String password,
      Role role, boolean isLocked) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.role = role;
    this.isLocked = isLocked;
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

}
