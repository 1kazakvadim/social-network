package by.sam_solutions.kazak.social_network.dto;

import java.time.LocalDate;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

public class ProfileDTO {

  private String firstname;
  private String lastname;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;
  private String gender;
  private String email;
  private String password;
  private String confirmPassword;
  private String termsAndConditions;

  public ProfileDTO() {
  }

  public ProfileDTO(String firstname, String lastname, LocalDate birthday, String gender,
      String email, String password, String confirmPassword, String termsAndConditions) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthday = birthday;
    this.gender = gender;
    this.email = email;
    this.password = password;
    this.confirmPassword = confirmPassword;
    this.termsAndConditions = termsAndConditions;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public LocalDate getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
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

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public String getTermsAndConditions() {
    return termsAndConditions;
  }

  public void setTermsAndConditions(String termsAndConditions) {
    this.termsAndConditions = termsAndConditions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProfileDTO that = (ProfileDTO) o;
    return Objects.equals(firstname, that.firstname) &&
        Objects.equals(lastname, that.lastname) &&
        Objects.equals(birthday, that.birthday) &&
        Objects.equals(gender, that.gender) &&
        Objects.equals(email, that.email) &&
        Objects.equals(password, that.password) &&
        Objects.equals(confirmPassword, that.confirmPassword) &&
        Objects.equals(termsAndConditions, that.termsAndConditions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname, birthday, gender, email, password, confirmPassword,
        termsAndConditions);
  }

}
