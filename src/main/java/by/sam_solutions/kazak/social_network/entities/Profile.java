package by.sam_solutions.kazak.social_network.entities;

import java.util.Objects;
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
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "basic_information_id")
  private BasicInformation basicInformation;

  @Column(name = "mobile_phone")
  private String mobilePhone;

  @Column(name = "home_phone")
  private String homePhone;

  @Column(name = "skype_name")
  private String skypeName;

  @Column(name = "instagram_name")
  private String instagramName;

  @Column(name = "country")
  private String country;

  @Column(name = "city")
  private String city;

  @Column(name = "profile_photo_name")
  private String profilePhotoName;

  @Column(name = "friend_count")
  private Integer friendCount;

  public Profile() {
  }

  public Profile(Integer id, User user,
      BasicInformation basicInformation, String mobilePhone, String homePhone,
      String skypeName, String instagramName, String country, String city,
      String profilePhotoName, Integer friendCount) {
    this.id = id;
    this.user = user;
    this.basicInformation = basicInformation;
    this.mobilePhone = mobilePhone;
    this.homePhone = homePhone;
    this.skypeName = skypeName;
    this.instagramName = instagramName;
    this.country = country;
    this.city = city;
    this.profilePhotoName = profilePhotoName;
    this.friendCount = friendCount;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public String getSkypeName() {
    return skypeName;
  }

  public void setSkypeName(String skypeName) {
    this.skypeName = skypeName;
  }

  public String getInstagramName() {
    return instagramName;
  }

  public void setInstagramName(String instagramName) {
    this.instagramName = instagramName;
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

  public String getProfilePhotoName() {
    return profilePhotoName;
  }

  public void setProfilePhotoName(String profilePhotoName) {
    this.profilePhotoName = profilePhotoName;
  }

  public Integer getFriendCount() {
    return friendCount;
  }

  public void setFriendCount(Integer friendCount) {
    this.friendCount = friendCount;
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
        Objects.equals(mobilePhone, profile.mobilePhone) &&
        Objects.equals(homePhone, profile.homePhone) &&
        Objects.equals(skypeName, profile.skypeName) &&
        Objects.equals(instagramName, profile.instagramName) &&
        Objects.equals(country, profile.country) &&
        Objects.equals(city, profile.city) &&
        Objects.equals(profilePhotoName, profile.profilePhotoName) &&
        Objects.equals(friendCount, profile.friendCount);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, user, basicInformation, mobilePhone, homePhone, skypeName, instagramName, country,
            city, profilePhotoName, friendCount);
  }

}
