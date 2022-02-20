package by.sam_solutions.kazak.social_network.converters;

import by.sam_solutions.kazak.social_network.dto.BasicInformationDTO;
import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.Gender;
import by.sam_solutions.kazak.social_network.services.RelationshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.converters.TwoWayConverter;
import org.springframework.stereotype.Component;

@Component
public class BasicInformationConverter implements TwoWayConverter {

  private static final Logger logger = LoggerFactory.getLogger(BasicInformationConverter.class);

  @Autowired
  private RelationshipService relationshipService;

  @Override
  public Class getSourceClass() {
    return BasicInformation.class;
  }

  @Override
  public Class getTargetClass() {
    return BasicInformationDTO.class;
  }

  @Override
  public Object convertTargetToSourceClass(Object target, Class sourceClass) throws Exception {
    if (target == null) {
      logger.debug("IllegalArgumentException {}", BasicInformationConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s", BasicInformationConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(sourceClass)
        || !this.getTargetClass().isAssignableFrom(target.getClass())) {
      logger.debug("IllegalArgumentException {}", BasicInformationConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("Illegal arguments %s", BasicInformationConverter.class.getName()));
    }
    BasicInformationDTO targetBasicInformation = (BasicInformationDTO) target;
    BasicInformation sourceBasicInformation = (BasicInformation) sourceClass.getDeclaredConstructor()
        .newInstance();
    sourceBasicInformation.setId(targetBasicInformation.getId());
    sourceBasicInformation.setFirstname(targetBasicInformation.getFirstname());
    sourceBasicInformation.setLastname(targetBasicInformation.getLastname());
    sourceBasicInformation.setGender(targetBasicInformation.getGender().getName());
    sourceBasicInformation.setBirthday(targetBasicInformation.getBirthday());
    sourceBasicInformation.setRelationship(
        relationshipService.getById(targetBasicInformation.getRelationshipId()));
    return sourceBasicInformation;
  }

  @Override
  public Object convertSourceToTargetClass(Object source, Class targetClass) throws Exception {
    if (source == null) {
      logger.debug("IllegalArgumentException {}", BasicInformationConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s", BasicInformationConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(source.getClass())
        || !this.getTargetClass().isAssignableFrom(targetClass)) {
      logger.debug("IllegalArgumentException {}", BasicInformationConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s", BasicInformationConverter.class.getName()));
    }
    BasicInformation sourceBasicInformation = (BasicInformation) source;
    BasicInformationDTO targetBasicInformation = (BasicInformationDTO) targetClass.getDeclaredConstructor()
        .newInstance();
    targetBasicInformation.setId(sourceBasicInformation.getId());
    targetBasicInformation.setFirstname(sourceBasicInformation.getFirstname());
    targetBasicInformation.setLastname(sourceBasicInformation.getLastname());
    targetBasicInformation.setGender(Gender.valueOf(sourceBasicInformation.getGender()));
    targetBasicInformation.setBirthday(sourceBasicInformation.getBirthday());
    targetBasicInformation.setRelationshipId(sourceBasicInformation.getRelationship().getId());
    return targetBasicInformation;
  }

}
