package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.dto.BasicInformationDTO;
import by.sam_solutions.kazak.social_network.dto.ContactInformationDTO;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.facades.BasicInformationFacade;
import by.sam_solutions.kazak.social_network.facades.CountryFacade;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import by.sam_solutions.kazak.social_network.facades.RelationshipFacade;
import by.sam_solutions.kazak.social_network.facades.RoleFacade;
import by.sam_solutions.kazak.social_network.facades.UserFacade;
import by.sam_solutions.kazak.social_network.validators.BasicInformationDtoValidator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/admin/profiles")
public class AdminController {

  @Autowired
  private ProfileFacade profileFacade;

  @Autowired
  private UserFacade userFacade;

  @Autowired
  private CountryFacade countryFacade;

  @Autowired
  private RelationshipFacade relationshipFacade;

  @Autowired
  private RoleFacade roleFacade;

  @Autowired
  private BasicInformationFacade basicInformationFacade;

  @Autowired
  private BasicInformationDtoValidator basicInformationDtoValidator;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/")
  public String getProfilesPage(Model model) {
    List<Profile> profiles = profileFacade.getAll();
    model.addAttribute("profiles", profiles);
    return "profiles";
  }

  @GetMapping("/{profileId}/edit")
  public ModelAndView getProfileEditPageByAdmin(HttpServletRequest request,
      ModelAndView modelAndView, @PathVariable Long profileId) {
    ContactInformationDTO contactInformationDTO = new ContactInformationDTO();
    BasicInformationDTO basicInformationDTO = new BasicInformationDTO();
    modelAndView.addObject("contactInformationDTO", contactInformationDTO);
    modelAndView.addObject("basicInformationDTO", basicInformationDTO);
    modelAndView.addObject("profile", profileFacade.getById(profileId));
    modelAndView.addObject("countries", countryFacade.getAll());
    modelAndView.addObject("relationships", relationshipFacade.getAll());
    modelAndView.addObject("roles", roleFacade.getAll());
    Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
    if (inputFlashMap != null) {
      modelAndView.addObject("profileErrors", inputFlashMap.get("profileErrors"));
      modelAndView.addObject("basicErrors", inputFlashMap.get("basicErrors"));
      modelAndView.addObject("emailMessageError", inputFlashMap.get("emailMessageError"));
      modelAndView.addObject("passwordMessageError", inputFlashMap.get("passwordMessageError"));
      modelAndView.addObject("profileMessageSuccess", inputFlashMap.get("profileMessageSuccess"));
      modelAndView.addObject("basicMessageSuccess", inputFlashMap.get("basicMessageSuccess"));
      modelAndView.addObject("emailMessageSuccess", inputFlashMap.get("emailMessageSuccess"));
      modelAndView.addObject("passwordMessageSuccess", inputFlashMap.get("passwordMessageSuccess"));
      modelAndView.addObject("roleMessageSuccess", inputFlashMap.get("roleMessageSuccess"));
      modelAndView.addObject("lockMessageSuccess", inputFlashMap.get("lockMessageSuccess"));
    }
    modelAndView.setViewName("admin-profile-edit");
    return modelAndView;
  }

  @PostMapping("/{profileId}/edit/profile")
  public ModelAndView saveProfileInfoByAdmin(ModelAndView modelAndView,
      @PathVariable Long profileId,
      @Valid @ModelAttribute("contactInformationDTO") ContactInformationDTO contactInformationDTO,
      BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("profileErrors", result);
      modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
      return modelAndView;
    }
    profileFacade.updateContactInformationInProfile(contactInformationDTO);
    redirectAttributes.addFlashAttribute("profileMessageSuccess",
        messageSource.getMessage("adminProfileEditPage.success.profileInformationUpdated", null,
            locale));
    modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
    return modelAndView;
  }

  @PostMapping("/{profileId}/edit/basic")
  public ModelAndView saveProfileBasicInfoByAdmin(ModelAndView modelAndView,
      @PathVariable Long profileId,
      @Valid @ModelAttribute("basicInformationDTO") BasicInformationDTO basicInformationDTO,
      BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
    basicInformationDtoValidator.validate(basicInformationDTO, result);
    if (result.hasErrors()) {
      redirectAttributes.addFlashAttribute("basicErrors", result);
      modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
      return modelAndView;
    }
    basicInformationFacade.updateBasicInformation(basicInformationDTO);
    redirectAttributes.addFlashAttribute("basicMessageSuccess",
        messageSource.getMessage("adminProfileEditPage.success.basicInformationUpdated", null,
            locale));
    modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
    return modelAndView;
  }

  @PostMapping("/{profileId}/edit/email-change")
  public ModelAndView saveProfileEmailChangeByAdmin(ModelAndView modelAndView,
      @PathVariable Long profileId,
      @RequestParam("email") String email, RedirectAttributes redirectAttributes, Locale locale) {
    Profile profile = profileFacade.getById(profileId);
    if (null == profile) {
      modelAndView.setViewName("redirect:/admin/profiles/");
      return modelAndView;
    }
    if (!userFacade.isEmailValid(email)) {
      redirectAttributes.addFlashAttribute("emailMessageError",
          messageSource.getMessage("adminProfileEditPage.error.isEmailValid", null, locale));
      modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
      return modelAndView;
    }
    if (userFacade.isEmailExists(email)) {
      redirectAttributes.addFlashAttribute("emailMessageError",
          messageSource.getMessage("adminProfileEditPage.error.isEmailExists", null, locale));
      modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
      return modelAndView;
    }
    userFacade.changeEmail(profile.getUser().getId(), email);
    redirectAttributes.addFlashAttribute("emailMessageSuccess",
        messageSource.getMessage("adminProfileEditPage.success.emailChanged", null,
            locale));
    modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
    return modelAndView;
  }

  @PostMapping("/{profileId}/edit/password-change")
  public ModelAndView saveProfilePasswordChangeByAdmin(ModelAndView modelAndView,
      @PathVariable Long profileId, @RequestParam("newPassword") String newPassword,
      @RequestParam("confirmPassword") String confirmPassword,
      RedirectAttributes redirectAttributes, Locale locale) {
    Profile profile = profileFacade.getById(profileId);
    if (profile == null) {
      modelAndView.setViewName("redirect:/admin/profiles/");
      return modelAndView;
    }
    if (!userFacade.isPasswordMatchConfirmPassword(newPassword, confirmPassword)) {
      redirectAttributes.addFlashAttribute("passwordMessageError",
          messageSource.getMessage("adminProfileEditPage.error.passwordMatch", null, locale));
      modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
      return modelAndView;
    }
    if (!userFacade.isPasswordValid(newPassword)) {
      redirectAttributes.addFlashAttribute("passwordMessageError",
          messageSource.getMessage("adminProfileEditPage.error.isPasswordValid", null, locale));
      modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
      return modelAndView;
    }
    userFacade.changePassword(profile.getUser().getId(), newPassword);
    redirectAttributes.addFlashAttribute("passwordMessageSuccess",
        messageSource.getMessage("adminProfileEditPage.success.passwordChanged", null,
            locale));
    modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
    return modelAndView;
  }

  @PostMapping("/{profileId}/edit/role-change")
  public ModelAndView saveProfileRoleChangeByAdmin(ModelAndView modelAndView,
      @PathVariable Long profileId,
      @RequestParam("role") Long roleId, RedirectAttributes redirectAttributes, Locale locale) {
    Profile profile = profileFacade.getById(profileId);
    if (profile == null) {
      modelAndView.setViewName("redirect:/admin/profiles/");
      return modelAndView;
    }
    roleFacade.changeProfileRole(profile, roleId);
    redirectAttributes.addFlashAttribute("roleMessageSuccess",
        messageSource.getMessage("adminProfileEditPage.success.roleChanged", null,
            locale));
    modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
    return modelAndView;
  }

  @PostMapping("/{profileId}/edit/lock-change")
  public ModelAndView saveProfileLockingChangeByAdmin(ModelAndView modelAndView,
      @PathVariable Long profileId,
      @RequestParam("isLocked") boolean isLocked, RedirectAttributes redirectAttributes,
      Locale locale) {
    Profile profile = profileFacade.getById(profileId);
    if (profile == null) {
      modelAndView.setViewName("redirect:/admin/profiles/");
      return modelAndView;
    }
    profileFacade.changeProfileLock(profile, isLocked);
    redirectAttributes.addFlashAttribute("lockMessageSuccess",
        messageSource.getMessage("adminProfileEditPage.success.lockChanged", null,
            locale));
    modelAndView.setViewName("redirect:/admin/profiles/" + profileId + "/edit");
    return modelAndView;
  }

}
