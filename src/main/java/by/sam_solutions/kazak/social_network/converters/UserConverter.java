package by.sam_solutions.kazak.social_network.converters;

import by.sam_solutions.kazak.social_network.dto.UserDTO;
import by.sam_solutions.kazak.social_network.entities.User;
import java.sql.Timestamp;
import org.springframework.binding.convert.converters.TwoWayConverter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements TwoWayConverter {

  @Override
  public Object convertTargetToSourceClass(Object target, Class sourceClass) throws Exception {
    if (target == null
        || !this.getSourceClass().isAssignableFrom(sourceClass)
        || !this.getTargetClass().isAssignableFrom(target.getClass())) {
      throw new IllegalArgumentException(
          String.format("Illegal arguments %s", UserConverter.class.getName()));
    }
    UserDTO targetUser = (UserDTO) target;
    User sourceUser = (User) sourceClass.getDeclaredConstructor().newInstance();
    sourceUser.setId(targetUser.getId());
    sourceUser.setEmail(targetUser.getEmail());
    sourceUser.setPassword(targetUser.getPassword());
    sourceUser.setRole(targetUser.getRole());
    sourceUser.setLocked(targetUser.isLocked());
    sourceUser.setTimeRegistration(Timestamp.valueOf(targetUser.getTimeRegistration()));
    return null;
  }

  @Override
  public Class getSourceClass() {
    return User.class;
  }

  @Override
  public Class getTargetClass() {
    return UserDTO.class;
  }

  @Override
  public Object convertSourceToTargetClass(Object source, Class targetClass) throws Exception {
    if (source == null
        || !this.getSourceClass().isAssignableFrom(source.getClass())
        || !this.getTargetClass().isAssignableFrom(targetClass)) {
      throw new IllegalArgumentException(
          String.format("Illegal arguments %s", UserConverter.class.getName()));
    }
    User sourceUser = (User) source;
    UserDTO targetUser = (UserDTO) targetClass.getDeclaredConstructor().newInstance();
    targetUser.setId(sourceUser.getId());
    targetUser.setEmail(sourceUser.getEmail());
    targetUser.setPassword(sourceUser.getPassword());
    targetUser.setRole(sourceUser.getRole());
    targetUser.setLocked(sourceUser.isLocked());
    targetUser.setTimeRegistration(sourceUser.getTimeRegistration().toLocalDateTime());
    return targetUser;
  }

}
