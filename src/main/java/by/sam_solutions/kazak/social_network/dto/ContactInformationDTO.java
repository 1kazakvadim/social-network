package by.sam_solutions.kazak.social_network.dto;

public class ContactInformationDTO {

  private Long id;
  private Long countryId;
  private String city;
  private String jobTitle;
  private String mobilePhone;
  private String homePhone;
  private String githubName;
  private String twitterName;
  private String instagramName;
  private String facebookName;
  private String skypeName;

  public ContactInformationDTO() {
  }

  public ContactInformationDTO(Long id, Long countryId, String city, String jobTitle,
      String mobilePhone, String homePhone, String githubName, String twitterName,
      String instagramName, String facebookName, String skypeName) {
    this.id = id;
    this.countryId = countryId;
    this.city = city;
    this.jobTitle = jobTitle;
    this.mobilePhone = mobilePhone;
    this.homePhone = homePhone;
    this.githubName = githubName;
    this.twitterName = twitterName;
    this.instagramName = instagramName;
    this.facebookName = facebookName;
    this.skypeName = skypeName;
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
