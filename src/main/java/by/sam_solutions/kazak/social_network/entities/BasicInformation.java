package by.sam_solutions.kazak.social_network.entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "basic_information")
public class BasicInformation implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "firstname", nullable = false)
  private String firstname;

  @Column(name = "lastname", nullable = false)
  private String lastname;

  @Column(name = "birthday", nullable = false)
  private LocalDate birthday;

  @Column(name = "gender")
  private String gender;

  @ManyToOne
  @JoinColumn(name = "relationship_id")
  private Relationship relationship;

  public BasicInformation() {
  }

  public BasicInformation(Long id, String firstname, String lastname, LocalDate birthday,
      String gender, Relationship relationship) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.birthday = birthday;
    this.gender = gender;
    this.relationship = relationship;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Relationship getRelationship() {
    return relationship;
  }

  public void setRelationship(Relationship relationship) {
    this.relationship = relationship;
  }

}
