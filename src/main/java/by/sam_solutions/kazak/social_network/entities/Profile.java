package by.sam_solutions.kazak.social_network.entities;

import java.time.LocalDateTime;
import java.util.Objects;
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
public class Profile {

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

  @Column(name = "country")
  private String country;

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

  @Column(name = "friend_count")
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
      BasicInformation basicInformation, String jobTitle, String country, String city,
      String mobilePhone, String homePhone, String githubName, String twitterName,
      String instagramName, String facebookName, String skypeName, Integer friendCount,
      String profilePhotoName, LocalDateTime timeRegistration, LocalDateTime updateTime) {
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

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(id, profile.id) &&
        Objects.equals(user, profile.user) &&
        Objects.equals(basicInformation, profile.basicInformation) &&
        Objects.equals(jobTitle, profile.jobTitle) &&
        Objects.equals(country, profile.country) &&
        Objects.equals(city, profile.city) &&
        Objects.equals(mobilePhone, profile.mobilePhone) &&
        Objects.equals(homePhone, profile.homePhone) &&
        Objects.equals(githubName, profile.githubName) &&
        Objects.equals(twitterName, profile.twitterName) &&
        Objects.equals(instagramName, profile.instagramName) &&
        Objects.equals(facebookName, profile.facebookName) &&
        Objects.equals(skypeName, profile.skypeName) &&
        Objects.equals(friendCount, profile.friendCount) &&
        Objects.equals(profilePhotoName, profile.profilePhotoName) &&
        Objects.equals(timeRegistration, profile.timeRegistration) &&
        Objects.equals(updateTime, profile.updateTime);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, user, basicInformation, jobTitle, country, city, mobilePhone, homePhone,
            githubName,
            twitterName, instagramName, facebookName, skypeName, friendCount, profilePhotoName,
            timeRegistration, updateTime);
  }

}
