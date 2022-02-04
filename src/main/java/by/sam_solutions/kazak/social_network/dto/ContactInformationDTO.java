package by.sam_solutions.kazak.social_network.dto;

import javax.validation.constraints.Size;

public class ContactInformationDTO {

  private Long id;
  private Long countryId;

  @Size(max = 255, message = "{registration.email.size}")
  private String city;

  @Size(max = 45)
  private String jobTitle;

  @Size(max = 45)
  private String mobilePhone;

  @Size(max = 45)
  private String homePhone;

  @Size(max = 45)
  private String githubName;

  @Size(max = 45)
  private String twitterName;

  @Size(max = 45)
  private String instagramName;

  @Size(max = 45)
  private String facebookName;

  @Size(max = 45)
  private String skypeName;

  public ContactInformationDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
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

}
