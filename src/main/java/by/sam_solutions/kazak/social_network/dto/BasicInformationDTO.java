package by.sam_solutions.kazak.social_network.dto;

import by.sam_solutions.kazak.social_network.entities.Gender;
import java.time.LocalDate;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public class BasicInformationDTO {

  private Long id;

  @Size(min = 1, max = 255)
  private String firstname;

  @Size(min = 1, max = 255)
  private String lastname;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthday;
  private Gender gender;
  private Long relationshipId;

  public BasicInformationDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Long getRelationshipId() {
    return relationshipId;
  }

  public void setRelationshipId(Long relationshipId) {
    this.relationshipId = relationshipId;
  }

}
