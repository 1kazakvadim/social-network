package by.sam_solutions.kazak.social_network.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.validator.constraints.Length;

@Entity
@Indexed
@Table(name = "profile")
public class Profile implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @ManyToOne
  @Cascade(CascadeType.SAVE_UPDATE)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;

  @ManyToOne
  @Cascade(CascadeType.SAVE_UPDATE)
  @IndexedEmbedded(includePaths = {"fullName"})
  @JoinColumn(name = "basic_information_id", nullable = false, unique = true)
  private BasicInformation basicInformation;

  @Column(name = "job_title")
  @Length(max = 45)
  private String jobTitle;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;

  @Column(name = "city")
  @Length(max = 255)
  private String city;

  @Column(name = "mobile_phone")
  @Length(max = 45)
  private String mobilePhone;

  @Column(name = "home_phone")
  @Length(max = 45)
  private String homePhone;

  @Column(name = "github_name")
  @Length(max = 45)
  private String githubName;

  @Column(name = "twitter_name")
  @Length(max = 45)
  private String twitterName;

  @Column(name = "instagram_name")
  @Length(max = 45)
  private String instagramName;

  @Column(name = "facebook_name")
  @Length(max = 45)
  private String facebookName;

  @Column(name = "skype_name")
  @Length(max = 45)
  private String skypeName;

  @Column(name = "profile_photo_name")
  @Length(max = 255)
  private String profilePhotoName;

  @Column(name = "time_registration", nullable = false)
  private LocalDateTime timeRegistration;

  @Column(name = "update_time", nullable = false)
  private LocalDateTime updateTime;

  public Profile() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public BasicInformation getBasicInformation() {
    return basicInformation;
  }

  public void setBasicInformation(
      BasicInformation basicInformation) {
    this.basicInformation = basicInformation;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public void setHomePhone(String homePhone) {
    this.homePhone = homePhone;
  }

  public String getGithubName() {
    return githubName;
  }

  public void setGithubName(String githubName) {
    this.githubName = githubName;
  }

  public String getTwitterName() {
    return twitterName;
  }

  public void setTwitterName(String twitterName) {
    this.twitterName = twitterName;
  }

  public String getInstagramName() {
    return instagramName;
  }

  public void setInstagramName(String instagramName) {
    this.instagramName = instagramName;
  }

  public String getFacebookName() {
    return facebookName;
  }

  public void setFacebookName(String facebookName) {
    this.facebookName = facebookName;
  }

  public String getSkypeName() {
    return skypeName;
  }

  public void setSkypeName(String skypeName) {
    this.skypeName = skypeName;
  }

  public String getProfilePhotoName() {
    return profilePhotoName;
  }

  public void setProfilePhotoName(String profilePhotoName) {
    this.profilePhotoName = profilePhotoName;
  }

  public LocalDateTime getTimeRegistration() {
    return timeRegistration;
  }

  public void setTimeRegistration(LocalDateTime timeRegistration) {
    this.timeRegistration = timeRegistration;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

}
