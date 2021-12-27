package by.sam_solutions.kazak.social_network.converters;

import by.sam_solutions.kazak.social_network.dto.ProfileDTO;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.User;
import org.springframework.binding.convert.converters.TwoWayConverter;
import org.springframework.stereotype.Component;

@Component
public class ProfileConverter implements TwoWayConverter {

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
      throw new NullPointerException(
          String.format("Null target argument %s", ProfileConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(sourceClass)
        || !this.getTargetClass().isAssignableFrom(target.getClass())) {
      throw new IllegalArgumentException(
          String.format("Illegal arguments %s", ProfileConverter.class.getName()));
    }
    ProfileDTO targetProfile = (ProfileDTO) target;
    Profile sourceProfile = (Profile) sourceClass.getDeclaredConstructor().newInstance();
    BasicInformation basicInformation = new BasicInformation();
    User user = new User();
    basicInformation.setFirstname(targetProfile.getFirstname());
    basicInformation.setLastname(targetProfile.getLastname());
    basicInformation.setBirthday(targetProfile.getBirthday());
    basicInformation.setGender(targetProfile.getGender());
    user.setEmail(targetProfile.getEmail());
    user.setPassword(targetProfile.getPassword());
    sourceProfile.setBasicInformation(basicInformation);
    sourceProfile.setUser(user);
    return sourceProfile;
  }

  @Override
  public Object convertSourceToTargetClass(Object source, Class targetClass) throws Exception {
    if (source == null) {
      String.format("Null source argument %s", ProfileConverter.class.getName());
    }
    if (!this.getSourceClass().isAssignableFrom(source.getClass())
        || !this.getTargetClass().isAssignableFrom(targetClass)) {
      throw new IllegalArgumentException(
          String.format("Illegal arguments %s", ProfileConverter.class.getName()));
    }
    Profile sourceProfile = (Profile) source;
    ProfileDTO targetProfile = (ProfileDTO) targetClass.getDeclaredConstructor().newInstance();
    targetProfile.setFirstname(sourceProfile.getBasicInformation().getFirstname());
    targetProfile.setLastname(sourceProfile.getBasicInformation().getLastname());
    targetProfile.setBirthday(sourceProfile.getBasicInformation().getBirthday());
    targetProfile.setGender(sourceProfile.getBasicInformation().getGender());
    targetProfile.setEmail(sourceProfile.getUser().getEmail());
    targetProfile.setPassword(sourceProfile.getUser().getPassword());
    return targetProfile;
  }

}
