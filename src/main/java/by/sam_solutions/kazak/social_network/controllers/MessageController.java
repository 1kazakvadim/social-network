package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.dto.MessageDTO;
import by.sam_solutions.kazak.social_network.entities.Dialog;
import by.sam_solutions.kazak.social_network.entities.Message;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import by.sam_solutions.kazak.social_network.facades.DialogFacade;
import by.sam_solutions.kazak.social_network.facades.MessageFacade;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/messages")
public class MessageController {

  @Autowired
  private ProfileFacade profileFacade;

  @Autowired
  private DialogFacade dialogFacade;

  @Autowired
  private MessageFacade messageFacade;

  @GetMapping("/")
  public ModelAndView getDialogsPage(ModelAndView modelAndView,
      @AuthenticationPrincipal UserPrincipal user) {
    Profile profile = profileFacade.getProfileByUserId(user.getId());
    List<Profile> profileList = dialogFacade.getProfilesWithDialogs(profile.getId());
    modelAndView.addObject("profiles", profileList);
    modelAndView.setViewName("dialogs");
    return modelAndView;
  }

  @GetMapping("/{profileId}")
  public ModelAndView getDialog(ModelAndView modelAndView,
      @PathVariable Long profileId, @AuthenticationPrincipal UserPrincipal user) {
    Profile profile = profileFacade.getProfileByUserId(user.getId());
    Profile recipientProfile = profileFacade.getById(profileId);
    if (recipientProfile == null || Objects.equals(profile.getId(), recipientProfile.getId())) {
      modelAndView.setViewName(WebConstants.REDIRECT_TO_MESSAGES);
      return modelAndView;
    }
    Dialog dialog = dialogFacade.getBySenderProfileIdAndRecipientProfileId(profile.getId(),
        profileId);
    if (dialog == null) {
      dialog = dialogFacade.createDialog(profile, recipientProfile);
    }
    List<Message> messages = messageFacade.getAllByDialogId(dialog.getId());
    List<Profile> profileList = dialogFacade.getProfilesWithDialogs(profile.getId());
    modelAndView.addObject("profiles", profileList);
    modelAndView.addObject("messages", messages);
    modelAndView.addObject("dialogId", dialog.getId());
    modelAndView.addObject("messageSenderProfileId", profile.getId());
    MessageDTO messageDto = new MessageDTO();
    modelAndView.addObject("messageDto", messageDto);
    modelAndView.setViewName("messages");
    return modelAndView;
  }

  @PostMapping("/{profileId}/send-message")
  public ModelAndView sendMessage(ModelAndView modelAndView, @PathVariable Long profileId,
      @ModelAttribute MessageDTO message) {
    messageFacade.sendMessage(message);
    modelAndView.setViewName(WebConstants.REDIRECT_TO_MESSAGES + profileId);
    return modelAndView;
  }

}
