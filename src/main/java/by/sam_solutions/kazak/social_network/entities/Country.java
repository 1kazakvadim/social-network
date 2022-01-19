package by.sam_solutions.kazak.social_network.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "iso_code", nullable = false, unique = true)
  private String ISOCode;

  public Country() {
  }

  public Country(Long id, String name, String ISOCode) {
    this.id = id;
    this.name = name;
    this.ISOCode = ISOCode;
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

  public String getISOCode() {
    return ISOCode;
  }

  public void setISOCode(String ISOCode) {
    this.ISOCode = ISOCode;
  }

}
