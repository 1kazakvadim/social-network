package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.dto.BasicInformationDTO;
import by.sam_solutions.kazak.social_network.dto.ContactInformationDTO;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import by.sam_solutions.kazak.social_network.facades.BasicInformationFacade;
import by.sam_solutions.kazak.social_network.facades.CountryFacade;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import by.sam_solutions.kazak.social_network.facades.RelationshipFacade;
import by.sam_solutions.kazak.social_network.facades.StorageFacade;
import by.sam_solutions.kazak.social_network.facades.UserFacade;
import by.sam_solutions.kazak.social_network.services.FriendService;
import by.sam_solutions.kazak.social_network.validators.BasicInformationDtoValidator;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class ProfileController {

  @Autowired
  private ProfileFacade profileFacade;

  @Autowired
  private UserFacade userFacade;

  @Autowired
  private FriendService friendService;

  @Autowired
  private StorageFacade storageFacade;

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

  @GetMapping("/id{profileId}")
  public ModelAndView getProfilePage(ModelAndView modelAndView, @PathVariable Long profileId) {
    Profile profile = profileFacade.getById(profileId);
    modelAndView.addObject("profile", profile);
    modelAndView.setViewName("profile");
    return modelAndView;
  }

  @GetMapping("/edit/profile")
  public ModelAndView getProfileEditPage(ModelAndView modelAndView, @AuthenticationPrincipal
      UserPrincipal user) {
    ContactInformationDTO contactInformationDTO = new ContactInformationDTO();
    modelAndView.addObject("contactInformationDTO", contactInformationDTO);
    modelAndView.addObject("profile", profileFacade.getProfileByUserId(user.getId()));
    modelAndView.addObject("countries", countryFacade.getAll());
    modelAndView.setViewName("profile-edit");
    return modelAndView;
  }

  @PostMapping("/edit/profile")
  public ModelAndView saveProfileEdit(ModelAndView modelAndView,
      @ModelAttribute("contactInformationDTO") ContactInformationDTO contactInformationDTO,
      BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
    profileFacade.updateContactInformationInProfile(contactInformationDTO);
    modelAndView.setViewName("profile-edit");
    return modelAndView;
  }

  @GetMapping("/edit/basic")
  public ModelAndView getProfileEditBasicPage(HttpServletRequest request, ModelAndView modelAndView,
      @AuthenticationPrincipal
          UserPrincipal user) {
    BasicInformationDTO basicInformationDTO = new BasicInformationDTO();
    modelAndView.addObject("basicInformationDTO", basicInformationDTO);
    modelAndView.addObject("profile", profileFacade.getProfileByUserId(user.getId()));
    modelAndView.addObject("relationships", relationshipFacade.getAll());
    Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
    if (inputFlashMap != null) {
      modelAndView.addObject("messageSuccess", inputFlashMap.get("messageSuccess"));
    }
    modelAndView.setViewName("profile-edit-basic");
    return modelAndView;
  }

  @PostMapping("/edit/basic")
  public ModelAndView saveProfileEditBasic(ModelAndView modelAndView,
      @ModelAttribute("basicInformationDTO") BasicInformationDTO basicInformationDTO,
      BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
    basicInformationDtoValidator.validate(basicInformationDTO, result);
    if (result.hasErrors()) {
      modelAndView.addObject("relationships", relationshipFacade.getAll());
      modelAndView.setViewName("profile-edit-basic");
      return modelAndView;
    }
    basicInformationFacade.updateBasicInformation(basicInformationDTO);
    redirectAttributes.addFlashAttribute("messageSuccess",
        messageSource.getMessage("basicInformationEditPage.success.informationUpdated", null,
            locale));
    modelAndView.setViewName("redirect:/edit/basic");
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
  public ModelAndView getProfilePasswordChangePage(ModelAndView modelAndView,
      @AuthenticationPrincipal
          UserPrincipal user) {
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
      @AuthenticationPrincipal
          UserPrincipal user) {
    modelAndView.addObject("email",
        profileFacade.getProfileByUserId(user.getId()).getUser().getEmail());
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
    modelAndView.setViewName("redirect:/logout");
    return modelAndView;
  }

  @PostMapping("/upload-profile-photo")
  public ModelAndView uploadProfilePhoto(ModelAndView modelAndView,
      @RequestParam("file") MultipartFile file,
      @AuthenticationPrincipal UserPrincipal user, Locale locale) {
    if (null == file) {
      modelAndView.addObject("messageError",
          messageSource.getMessage("profilePage.uploadProfilePhoto.error.empty", null, locale));
      modelAndView.setViewName("profile");
      return modelAndView;
    }
    if (!storageFacade.isMultipartFileValid(file)) {
      modelAndView.addObject("messageError",
          messageSource.getMessage("profilePage.uploadProfilePhoto.error.isMultipartFileValid",
              null, locale));
      modelAndView.setViewName("profile");
      return modelAndView;
    }
    storageFacade.uploadProfilePhoto(file, user);
    modelAndView.setViewName("redirect:/id" + user.getId());
    return modelAndView;
  }

  @GetMapping("/delete-profile-photo")
  public ModelAndView deleteProfilePhoto(ModelAndView modelAndView,
      @AuthenticationPrincipal UserPrincipal user) {
    storageFacade.deleteProfilePhoto(user);
    modelAndView.setViewName("redirect:/id" + user.getId());
    return modelAndView;
  }


}
