package by.sam_solutions.kazak.social_network.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "profile")
public class Profile implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "basic_information_id")
  private BasicInformation basicInformation;

  @Column(name = "job_title")
  private String jobTitle;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "country_id")
  private Country country;

  @Column(name = "city")
  private String city;

  @Column(name = "mobile_phone")
  private String mobilePhone;

  @Column(name = "home_phone")
  private String homePhone;

  @Column(name = "github_name")
  private String githubName;

  @Column(name = "twitter_name")
  private String twitterName;

  @Column(name = "instagram_name")
  private String instagramName;

  @Column(name = "facebook_name")
  private String facebookName;

  @Column(name = "skype_name")
  private String skypeName;

  @Column(name = "friend_count", columnDefinition = "0")
  private Integer friendCount;

  @Column(name = "profile_photo_name")
  private String profilePhotoName;

  @Column(name = "time_registration", nullable = false)
  private LocalDateTime timeRegistration;

  @Column(name = "update_time")
  private LocalDateTime updateTime;

  public Profile() {
  }

  public Profile(Long id, User user,
      BasicInformation basicInformation, String jobTitle,
      Country country, String city, String mobilePhone, String homePhone, String githubName,
      String twitterName, String instagramName, String facebookName, String skypeName,
      Integer friendCount, String profilePhotoName, LocalDateTime timeRegistration,
      LocalDateTime updateTime) {
    this.id = id;
    this.user = user;
    this.basicInformation = basicInformation;
    this.jobTitle = jobTitle;
    this.country = country;
    this.city = city;
    this.mobilePhone = mobilePhone;
    this.homePhone = homePhone;
    this.githubName = githubName;
    this.twitterName = twitterName;
    this.instagramName = instagramName;
    this.facebookName = facebookName;
    this.skypeName = skypeName;
    this.friendCount = friendCount;
    this.profilePhotoName = profilePhotoName;
    this.timeRegistration = timeRegistration;
    this.updateTime = updateTime;
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

  public Integer getFriendCount() {
    return friendCount;
  }

  public void setFriendCount(Integer friendCount) {
    this.friendCount = friendCount;
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
