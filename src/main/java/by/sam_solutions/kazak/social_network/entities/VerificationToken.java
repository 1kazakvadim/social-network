package by.sam_solutions.kazak.social_network.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "verification_token")
public class VerificationToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "token")
  private String token;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "expiry_date")
  private LocalDateTime expiryDate;

  public VerificationToken() {
  }

  public VerificationToken(User user) {
    this.user = user;
    this.token = UUID.randomUUID().toString();
    this.expiryDate = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public LocalDateTime getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(LocalDateTime expiryDate) {
    this.expiryDate = expiryDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerificationToken that = (VerificationToken) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(token, that.token) &&
        Objects.equals(user, that.user) &&
        Objects.equals(expiryDate, that.expiryDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, token, user, expiryDate);
  }

}
