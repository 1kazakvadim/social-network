package by.sam_solutions.kazak.social_network.converters;

import by.sam_solutions.kazak.social_network.dto.ProfileDTO;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Gender;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.convert.converters.TwoWayConverter;
import org.springframework.stereotype.Component;

@Component
public class ProfileConverter implements TwoWayConverter {

  private final Logger logger = LoggerFactory.getLogger(ProfileConverter.class);

  @Override
  public Class getSourceClass() {
    return Profile.class;
  }

  @Override
  public Class getTargetClass() {
    return ProfileDTO.class;
  }

  @Override
  public Object convertTargetToSourceClass(Object target, Class sourceClass) throws Exception {
    if (target == null) {
      logger.debug("IllegalArgumentException {}", ProfileConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s", ProfileConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(sourceClass)
        || !this.getTargetClass().isAssignableFrom(target.getClass())) {
      logger.debug("IllegalArgumentException {}", ProfileConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s", ProfileConverter.class.getName()));
    }
    ProfileDTO targetProfile = (ProfileDTO) target;
    Profile sourceProfile = (Profile) sourceClass.getDeclaredConstructor().newInstance();
    BasicInformation basicInformation = new BasicInformation();
    User user = new User();
    basicInformation.setId(targetProfile.getId());
    basicInformation.setFirstname(targetProfile.getFirstname());
    basicInformation.setLastname(targetProfile.getLastname());
    basicInformation.setBirthday(targetProfile.getBirthday());
    basicInformation.setGender(targetProfile.getGender().getName());
    user.setEmail(targetProfile.getEmail());
    user.setPassword(targetProfile.getPassword());
    sourceProfile.setBasicInformation(basicInformation);
    sourceProfile.setUser(user);
    return sourceProfile;
  }

  @Override
  public Object convertSourceToTargetClass(Object source, Class targetClass) throws Exception {
    if (source == null) {
      logger.debug("IllegalArgumentException {}", ProfileConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s", ProfileConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(source.getClass())
        || !this.getTargetClass().isAssignableFrom(targetClass)) {
      logger.debug("IllegalArgumentException {}", ProfileConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s", ProfileConverter.class.getName()));
    }
    Profile sourceProfile = (Profile) source;
    ProfileDTO targetProfile = (ProfileDTO) targetClass.getDeclaredConstructor().newInstance();
    targetProfile.setId(sourceProfile.getId());
    targetProfile.setFirstname(sourceProfile.getBasicInformation().getFirstname());
    targetProfile.setLastname(sourceProfile.getBasicInformation().getLastname());
    targetProfile.setBirthday(sourceProfile.getBasicInformation().getBirthday());
    targetProfile.setGender(Gender.valueOf(sourceProfile.getBasicInformation().getGender()));
    targetProfile.setEmail(sourceProfile.getUser().getEmail());
    targetProfile.setPassword(sourceProfile.getUser().getPassword());
    return targetProfile;
  }

}
