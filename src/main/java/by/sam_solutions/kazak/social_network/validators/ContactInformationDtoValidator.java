package by.sam_solutions.kazak.social_network.validators;

import by.sam_solutions.kazak.social_network.dto.ContactInformationDTO;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ContactInformationDtoValidator implements Validator {

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private ProfileService profileService;

  @Override
  public boolean supports(Class<?> clazz) {
    return clazz.equals(ContactInformationDTO.class);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ContactInformationDTO contactInformationDTO = (ContactInformationDTO) target;

    if (!contactInformationDTO.getMobilePhone().isBlank()) {
      if (!profileService.isPhoneFieldValid(contactInformationDTO.getMobilePhone())) {
        errors.rejectValue("mobilePhone", "profileEditPage.error.mobilePhone",
            messageSource.getMessage("profileEditPage.error.mobilePhone", null,
                Locale.getDefault()));
      }
    }
    if (!contactInformationDTO.getHomePhone().isBlank()) {
      if (!profileService.isPhoneFieldValid(contactInformationDTO.getHomePhone())) {
        errors.rejectValue("homePhone", "profileEditPage.error.homePhone",
            messageSource.getMessage("profileEditPage.error.homePhone", null,
                Locale.getDefault()));
      }
    }
    if (!contactInformationDTO.getGithubName().isBlank()) {
      if (!profileService.isFieldWithLinkValid(contactInformationDTO.getGithubName())) {
        errors.rejectValue("githubName", "profileEditPage.error.githubName",
            messageSource.getMessage("profileEditPage.error.githubName", null,
                Locale.getDefault()));
      }
    }
    if (!contactInformationDTO.getTwitterName().isBlank()) {
      if (!profileService.isFieldWithLinkValid(contactInformationDTO.getTwitterName())) {
        errors.rejectValue("twitterName", "profileEditPage.error.twitterName",
            messageSource.getMessage("profileEditPage.error.twitterName", null,
                Locale.getDefault()));
      }
    }
    if (!contactInformationDTO.getInstagramName().isBlank()) {
      if (!profileService.isFieldWithLinkValid(contactInformationDTO.getInstagramName())) {
        errors.rejectValue("instagramName", "profileEditPage.error.instagramName",
            messageSource.getMessage("profileEditPage.error.instagramName", null,
                Locale.getDefault()));
      }
    }
    if (!contactInformationDTO.getFacebookName().isBlank()) {
      if (!profileService.isFieldWithLinkValid(contactInformationDTO.getFacebookName())) {
        errors.rejectValue("facebookName", "profileEditPage.error.facebookName",
            messageSource.getMessage("profileEditPage.error.facebookName", null,
                Locale.getDefault()));
      }
    }
  }

}
