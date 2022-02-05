package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.dto.BasicInformationDTO;
import by.sam_solutions.kazak.social_network.dto.ContactInformationDTO;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import by.sam_solutions.kazak.social_network.facades.BasicInformationFacade;
import by.sam_solutions.kazak.social_network.facades.CountryFacade;
import by.sam_solutions.kazak.social_network.facades.PhotoFacade;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import by.sam_solutions.kazak.social_network.facades.RelationshipFacade;
import by.sam_solutions.kazak.social_network.facades.UserFacade;
import by.sam_solutions.kazak.social_network.validators.BasicInformationDtoValidator;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class SettingController {

  @Autowired
  private ProfileFacade profileFacade;

  @Autowired
  private UserFacade userFacade;

  @Autowired
  private PhotoFacade photoFacade;

  @Autowired
  private CountryFacade countryFacade;

  @Autowired
  private RelationshipFacade relationshipFacade;

  @Autowired
  private BasicInformationFacade basicInformationFacade;

  @Autowired
  private BasicInformationDtoValidator basicInformationDtoValidator;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/edit/profile")
  public ModelAndView getProfileEditPage(HttpServletRequest request, ModelAndView modelAndView,
      @AuthenticationPrincipal UserPrincipal user) {
    ContactInformationDTO contactInformationDTO = new ContactInformationDTO();
    modelAndView.addObject("contactInformationDTO", contactInformationDTO);
    modelAndView.addObject("profile", profileFacade.getProfileByUserId(user.getId()));
    modelAndView.addObject("countries", countryFacade.getAll());
    Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
    if (inputFlashMap != null) {
      modelAndView.addObject("errors", inputFlashMap.get("errors"));
      modelAndView.addObject("messageSuccess", inputFlashMap.get("messageSuccess"));
    }
    modelAndView.setViewName("profile-edit");
    return modelAndView;
  }

  @PostMapping("/edit/profile")
  public ModelAndView saveProfileEdit(ModelAndView modelAndView,
      @Valid @ModelAttribute("contactInformationDTO") ContactInformationDTO contactInformationDTO,
      BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("errors", result);
      modelAndView.setViewName(WebConstants.REDIRECT_TO_EDIT_PROFILE);
      return modelAndView;
    }
    profileFacade.updateContactInformationInProfile(contactInformationDTO);
    redirectAttributes.addFlashAttribute("messageSuccess",
        messageSource.getMessage("profileEditPage.success.informationUpdated", null,
            locale));
    modelAndView.setViewName(WebConstants.REDIRECT_TO_EDIT_PROFILE);
    return modelAndView;
  }

  @GetMapping("/edit/basic")
  public ModelAndView getProfileEditBasicPage(HttpServletRequest request, ModelAndView modelAndView,
      @AuthenticationPrincipal UserPrincipal user) {
    BasicInformationDTO basicInformationDTO = new BasicInformationDTO();
    modelAndView.addObject("basicInformationDTO", basicInformationDTO);
    modelAndView.addObject("profile", profileFacade.getProfileByUserId(user.getId()));
    modelAndView.addObject("relationships", relationshipFacade.getAll());
    Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
    if (inputFlashMap != null) {
      modelAndView.addObject("errors", inputFlashMap.get("errors"));
      modelAndView.addObject("messageSuccess", inputFlashMap.get("messageSuccess"));
    }
    modelAndView.setViewName("profile-edit-basic");
    return modelAndView;
  }

  @PostMapping("/edit/basic")
  public ModelAndView saveProfileEditBasic(ModelAndView modelAndView,
      @Valid @ModelAttribute("basicInformationDTO") BasicInformationDTO basicInformationDTO,
      BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
    basicInformationDtoValidator.validate(basicInformationDTO, result);
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("errors", result);
      modelAndView.setViewName(WebConstants.REDIRECT_TO_EDIT_BASIC);
      return modelAndView;
    }
    basicInformationFacade.updateBasicInformation(basicInformationDTO);
    redirectAttributes.addFlashAttribute("messageSuccess",
        messageSource.getMessage("basicInformationEditPage.success.informationUpdated", null,
            locale));
    modelAndView.setViewName(WebConstants.REDIRECT_TO_EDIT_BASIC);
    return modelAndView;
  }

  @GetMapping("/edit/security")
  public ModelAndView getProfileEditSecurityPage(ModelAndView modelAndView, @AuthenticationPrincipal
      UserPrincipal user) {
    modelAndView.addObject("profile", profileFacade.getProfileByUserId(user.getId()));
    modelAndView.setViewName("profile-edit-security");
    return modelAndView;
  }

  @GetMapping("/edit/security/password-change")
  public ModelAndView getProfilePasswordChangePage(ModelAndView modelAndView) {
    modelAndView.setViewName("password-change");
    return modelAndView;
  }

  @PostMapping("edit/security/password-change")
  public ModelAndView saveProfilePasswordChange(ModelAndView modelAndView,
      @RequestParam("password") String password, @RequestParam("newPassword") String newPassword,
      @RequestParam("confirmPassword") String confirmPassword,
      @AuthenticationPrincipal UserPrincipal user, Locale locale) {
    if (!userFacade.isUserPassword(user.getId(), password)) {
      modelAndView.addObject("messageError",
          messageSource.getMessage("passwordChangePage.error.isUserPassword", null, locale));
      modelAndView.setViewName("password-change");
      return modelAndView;
    }
    if (!userFacade.isPasswordMatchConfirmPassword(newPassword, confirmPassword)) {
      modelAndView.addObject("messageError",
          messageSource.getMessage("passwordChangePage.error.passwordMatch", null, locale));
      modelAndView.setViewName("password-change");
      return modelAndView;
    }
    if (!userFacade.isPasswordValid(newPassword)) {
      modelAndView.addObject("messageError",
          messageSource.getMessage("passwordChangePage.error.isPasswordValid", null, locale));
      modelAndView.setViewName("password-change");
      return modelAndView;
    }
    userFacade.changePassword(user.getId(), newPassword);
    modelAndView.addObject("messageSuccess",
        messageSource.getMessage("passwordChangePage.success.passwordChanged", null, locale));
    modelAndView.setViewName("password-change");
    return modelAndView;
  }

  @GetMapping("/edit/security/email-change")
  public ModelAndView getProfileEmailChangePage(ModelAndView modelAndView,
      @AuthenticationPrincipal UserPrincipal user) {
    modelAndView.addObject("email",
        user.getUsername());
    modelAndView.setViewName("email-change");
    return modelAndView;
  }

  @PostMapping("/edit/security/email-change")
  public ModelAndView saveProfileEmailChange(ModelAndView modelAndView,
      @RequestParam("email") String email, @AuthenticationPrincipal UserPrincipal user,
      Locale locale) {
    if (!userFacade.isEmailValid(email)) {
      modelAndView.addObject("messageError",
          messageSource.getMessage("emailChangePage.error.isEmailValid", null, locale));
      modelAndView.setViewName("email-change");
      return modelAndView;
    }
    if (userFacade.isEmailExists(email)) {
      modelAndView.addObject("messageError",
          messageSource.getMessage("emailChangePage.error.isEmailExists", null, locale));
      modelAndView.setViewName("email-change");
      return modelAndView;
    }
    userFacade.changeEmail(user.getId(), email);
    modelAndView.addObject("email", email);
    modelAndView.addObject("messageSuccess",
        messageSource.getMessage("emailChangePage.success.emailChanged", null, locale));
    modelAndView.setViewName("email-change");
    return modelAndView;
  }

  @GetMapping("/edit/security/delete-profile")
  public ModelAndView deleteProfile(ModelAndView modelAndView,
      @AuthenticationPrincipal UserPrincipal user) {
    userFacade.disableUser(user);
    modelAndView.setViewName(WebConstants.REDIRECT_PREFIX + "/login");
    return modelAndView;
  }

  @PostMapping("/upload-profile-photo")
  public ModelAndView uploadProfilePhoto(ModelAndView modelAndView,
      @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
      @AuthenticationPrincipal UserPrincipal user, Locale locale) {
    if (file == null) {
      redirectAttributes.addFlashAttribute("error",
          messageSource.getMessage("uploadPage.error.empty", null, locale));
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + user.getId());
      return modelAndView;
    }
    if (!photoFacade.isMultipartFileValid(file)) {
      redirectAttributes.addFlashAttribute("error",
          messageSource.getMessage("uploadPage.error.isMultipartFileValid", null, locale));
      modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + user.getId());
      return modelAndView;
    }
    Profile profile = profileFacade.getProfileByUserId(user.getId());
    photoFacade.uploadProfilePhoto(file, profile);
    modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + user.getId());
    return modelAndView;
  }

  @GetMapping("/delete-profile-photo")
  public ModelAndView deleteProfilePhoto(ModelAndView modelAndView,
      @AuthenticationPrincipal UserPrincipal user) {
    Profile profile = profileFacade.getProfileByUserId(user.getId());
    photoFacade.deleteProfilePhoto(profile);
    modelAndView.setViewName(WebConstants.REDIRECT_TO_PROFILE + user.getId());
    return modelAndView;
  }

}
