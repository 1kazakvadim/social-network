package by.sam_solutions.kazak.social_network.validators;

import by.sam_solutions.kazak.social_network.dto.ProfileDTO;
import by.sam_solutions.kazak.social_network.services.BasicInformationService;
import by.sam_solutions.kazak.social_network.services.UserService;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProfileDtoValidator implements Validator {

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private UserService userService;

  @Autowired
  private BasicInformationService basicInformationService;

  @Override
  public boolean supports(Class clazz) {
    return clazz.equals(ProfileDTO.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ProfileDTO profileDTO = (ProfileDTO) target;
    if (StringUtils.isBlank(profileDTO.getFirstname())) {
      errors.rejectValue("firstname", "registerPage.error.firstname",
          messageSource.getMessage("registerPage.error.firstname", null, Locale.getDefault()));
    } else if (StringUtils.isBlank(profileDTO.getLastname())) {
      errors.rejectValue("lastname", "registerPage.error.lastname",
          messageSource.getMessage("registerPage.error.lastname", null, Locale.getDefault()));
    } else if (null == profileDTO.getBirthday()) {
      errors.rejectValue("birthday", "registerPage.error.birthday",
          messageSource.getMessage("registerPage.error.birthday", null, Locale.getDefault()));
    } else if (StringUtils.isBlank(profileDTO.getGender())) {
      errors.rejectValue("gender", "registerPage.error.gender",
          messageSource.getMessage("registerPage.error.gender", null, Locale.getDefault()));
    } else if (StringUtils.isBlank(profileDTO.getEmail())) {
      errors.rejectValue("email", "registerPage.error.email",
          messageSource.getMessage("registerPage.error.email", null, Locale.getDefault()));
    } else if (StringUtils.isBlank(profileDTO.getPassword())) {
      errors.rejectValue("password", "registerPage.error.password",
          messageSource.getMessage("registerPage.error.password", null, Locale.getDefault()));
    } else if (null == profileDTO.getTermsAndConditions() || !profileDTO.getTermsAndConditions()
        .equals("true")) {
      errors.rejectValue("termsAndConditions", "registerPage.error.termsAndConditions",
          messageSource
              .getMessage("registerPage.error.termsAndConditions", null, Locale.getDefault()));
    } else if (!basicInformationService.isBirthdayDateValid(profileDTO.getBirthday())) {
      errors.rejectValue("birthday", "registerPage.error.birthdayDate",
          messageSource.getMessage("registerPage.error.birthdayDate", null,
              Locale.getDefault()));
    } else if (!basicInformationService.isGenderValid(profileDTO.getGender())) {
      errors.rejectValue("gender", "registerPage.error.genderType",
          messageSource.getMessage("registerPage.error.genderType", null,
              Locale.getDefault()));
    } else if (!userService.isEmailValid(profileDTO.getEmail())) {
      errors.rejectValue("email", "registerPage.error.isEmailValid",
          messageSource.getMessage("registerPage.error.isEmailValid", null,
              Locale.getDefault()));
    } else if (userService.isEmailExists(profileDTO.getEmail())) {
      errors.rejectValue("email", "registerPage.error.isEmailExists",
          messageSource.getMessage("registerPage.error.isEmailExists", null,
              Locale.getDefault()));
    } else if (!userService.isPasswordMatchConfirmPassword(profileDTO.getPassword(),
        profileDTO.getConfirmPassword())) {
      errors.rejectValue("password", "registerPage.error.passwordMatchConfirmPassword",
          messageSource.getMessage("registerPage.error.passwordMatchConfirmPassword", null,
              Locale.getDefault()));
    } else if (!userService.isPasswordValid(profileDTO.getPassword())) {
      errors.rejectValue("password", "registerPage.error.isPasswordValid",
          messageSource.getMessage("registerPage.error.isPasswordValid", null,
              Locale.getDefault()));
    }
  }

}
