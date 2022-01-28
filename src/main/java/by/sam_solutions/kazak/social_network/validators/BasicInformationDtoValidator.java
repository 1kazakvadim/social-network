package by.sam_solutions.kazak.social_network.validators;

import by.sam_solutions.kazak.social_network.dto.BasicInformationDTO;
import by.sam_solutions.kazak.social_network.services.BasicInformationService;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BasicInformationDtoValidator implements Validator {

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private BasicInformationService basicInformationService;

  @Autowired
  private ProfileService profileService;

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.equals(BasicInformationDTO.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    BasicInformationDTO basicInformationDTO = (BasicInformationDTO) target;

    if (StringUtils.isBlank(basicInformationDTO.getFirstname())) {
      errors.rejectValue("firstname", "basicInformationEditPage.error.firstname",
          messageSource.getMessage("basicInformationEditPage.error.firstname", null,
              Locale.getDefault()));
    } else if (profileService.isFieldContainsSpecialCharacters(
        basicInformationDTO.getFirstname())) {
      errors.rejectValue("firstname", "basicInformationEditPage.error.specialCharactersFirstname",
          messageSource.getMessage("basicInformationEditPage.error.specialCharactersFirstname",
              null,
              Locale.getDefault()));
    }

    if (StringUtils.isBlank(basicInformationDTO.getLastname())) {
      errors.rejectValue("lastname", "basicInformationEditPage.error.lastname",
          messageSource.getMessage("basicInformationEditPage.error.lastname", null,
              Locale.getDefault()));
    } else if (profileService.isFieldContainsSpecialCharacters(basicInformationDTO.getLastname())) {
      errors.rejectValue("lastname", "basicInformationEditPage.error.specialCharactersLastname",
          messageSource.getMessage("basicInformationEditPage.error.specialCharactersLastname", null,
              Locale.getDefault()));
    }

    if (!basicInformationService.isGenderValid(basicInformationDTO.getGender())) {
      errors.rejectValue("gender", "basicInformationEditPage.error.genderType",
          messageSource.getMessage("basicInformationEditPage.error.genderType", null,
              Locale.getDefault()));
    }

    if (null == basicInformationDTO.getBirthday()) {
      errors.rejectValue("birthday", "basicInformationEditPage.error.birthday",
          messageSource.getMessage("basicInformationEditPage.error.birthday", null,
              Locale.getDefault()));
    } else if (!basicInformationService.isBirthdayDateValid(basicInformationDTO.getBirthday())) {
      errors.rejectValue("birthday", "basicInformationEditPage.error.birthdayDate",
          messageSource.getMessage("basicInformationEditPage.error.birthdayDate", null,
              Locale.getDefault()));
    }
  }

}
