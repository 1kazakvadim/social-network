package by.sam_solutions.kazak.social_network.converters;

import by.sam_solutions.kazak.social_network.dto.UserDTO;
import by.sam_solutions.kazak.social_network.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.convert.converters.TwoWayConverter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements TwoWayConverter {

  private final Logger logger = LoggerFactory.getLogger(UserConverter.class);

  @Override
  public Class getSourceClass() {
    return User.class;
  }

  @Override
  public Class getTargetClass() {
    return UserDTO.class;
  }

  @Override
  public Object convertTargetToSourceClass(Object target, Class sourceClass) throws Exception {
    if (target == null) {
      logger.debug("IllegalArgumentException {}", UserConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s", UserConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(sourceClass)
        || !this.getTargetClass().isAssignableFrom(target.getClass())) {
      logger.debug("IllegalArgumentException {}", UserConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s", UserConverter.class.getName()));
    }
    UserDTO targetUser = (UserDTO) target;
    User sourceUser = (User) sourceClass.getDeclaredConstructor().newInstance();
    sourceUser.setId(targetUser.getId());
    sourceUser.setEmail(targetUser.getEmail());
    sourceUser.setPassword(targetUser.getPassword());
    sourceUser.setRole(targetUser.getRole());
    sourceUser.setLocked(targetUser.isLocked());
    return sourceUser;
  }

  @Override
  public Object convertSourceToTargetClass(Object source, Class targetClass) throws Exception {
    if (source == null) {
      logger.debug("IllegalArgumentException {}", UserConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s", UserConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(source.getClass())
        || !this.getTargetClass().isAssignableFrom(targetClass)) {
      logger.debug("IllegalArgumentException {}", UserConverter.class.getName());
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
    return targetUser;
  }

}
