package by.sam_solutions.kazak.social_network.converters;

import by.sam_solutions.kazak.social_network.dto.ContactInformationDTO;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.services.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.converters.TwoWayConverter;
import org.springframework.stereotype.Component;

@Component
public class ContactInformationConverter implements TwoWayConverter {

  private static final Logger logger = LoggerFactory.getLogger(ContactInformationConverter.class);

  @Autowired
  private CountryService countryService;

  @Override
  public Class getSourceClass() {
    return Profile.class;
  }

  @Override
  public Class getTargetClass() {
    return ContactInformationDTO.class;
  }

  @Override
  public Object convertTargetToSourceClass(Object target, Class sourceClass) throws Exception {
    if (target == null) {
      logger.debug("IllegalArgumentException {}", ContactInformationConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s",
              ContactInformationConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(sourceClass)
        || !this.getTargetClass().isAssignableFrom(target.getClass())) {
      logger.debug("IllegalArgumentException {}", ContactInformationConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("Illegal arguments %s", ContactInformationConverter.class.getName()));
    }
    ContactInformationDTO targetContactInformation = (ContactInformationDTO) target;
    Profile sourceProfile = (Profile) sourceClass.getDeclaredConstructor()
        .newInstance();
    sourceProfile.setId(targetContactInformation.getId());
    sourceProfile.setCountry(countryService.getById(targetContactInformation.getCountryId()));
    sourceProfile.setCity(targetContactInformation.getCity());
    sourceProfile.setJobTitle(targetContactInformation.getJobTitle());
    sourceProfile.setMobilePhone(targetContactInformation.getMobilePhone());
    sourceProfile.setHomePhone(targetContactInformation.getHomePhone());
    sourceProfile.setGithubName(targetContactInformation.getGithubName());
    sourceProfile.setTwitterName(targetContactInformation.getTwitterName());
    sourceProfile.setInstagramName(targetContactInformation.getInstagramName());
    sourceProfile.setFacebookName(targetContactInformation.getFacebookName());
    sourceProfile.setSkypeName(targetContactInformation.getSkypeName());
    return sourceProfile;
  }

  @Override
  public Object convertSourceToTargetClass(Object source, Class targetClass) throws Exception {
    if (source == null) {
      logger.debug("IllegalArgumentException {}", ContactInformationConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s",
              ContactInformationConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(source.getClass())
        || !this.getTargetClass().isAssignableFrom(targetClass)) {
      logger.debug("IllegalArgumentException {}", ContactInformationConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s",
              ContactInformationConverter.class.getName()));
    }
    Profile sourceProfile = (Profile) source;
    ContactInformationDTO targetContactInformation = (ContactInformationDTO) targetClass.getDeclaredConstructor()
        .newInstance();
    targetContactInformation.setId(sourceProfile.getId());
    targetContactInformation.setCountryId(sourceProfile.getCountry().getId());
    targetContactInformation.setCity(sourceProfile.getCity());
    targetContactInformation.setJobTitle(sourceProfile.getJobTitle());
    targetContactInformation.setMobilePhone(sourceProfile.getMobilePhone());
    targetContactInformation.setHomePhone(sourceProfile.getHomePhone());
    targetContactInformation.setGithubName(sourceProfile.getGithubName());
    targetContactInformation.setTwitterName(sourceProfile.getTwitterName());
    targetContactInformation.setInstagramName(sourceProfile.getInstagramName());
    targetContactInformation.setFacebookName(sourceProfile.getFacebookName());
    targetContactInformation.setSkypeName(sourceProfile.getSkypeName());
    return targetContactInformation;
  }

}
