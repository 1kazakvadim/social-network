package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Dialog;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.facades.DialogFacade;
import by.sam_solutions.kazak.social_network.services.DialogService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DialogFacadeImpl implements DialogFacade {

  @Autowired
  private DialogService dialogService;

  @Override
  public List<Dialog> getAllByProfileId(Long id) {
    return dialogService.getAllByProfileId(id);
  }

  @Override
  public void deleteById(Long id) {
    dialogService.deleteById(id);
  }

  @Override
  public List<Profile> getProfilesWithDialogs(Long profileId) {
    List<Dialog> dialogs = dialogService.getAllByProfileId(profileId);
    return dialogService.getProfilesWithDialogs(dialogs, profileId);
  }

  @Override
  public Dialog getBySenderProfileIdAndRecipientProfileId(Long senderProfileId,
      Long recipientProfileId) {
    return dialogService.getBySenderProfileIdAndRecipientProfileId(senderProfileId,
        recipientProfileId);
  }

  @Override
  public Dialog createDialog(Profile senderProfile, Profile recipientProfile) {
    Dialog dialog = new Dialog();
    dialog.setSenderProfile(senderProfile);
    dialog.setRecipientProfile(recipientProfile);
    dialog.setTimeCreation(LocalDateTime.now());
    dialogService.saveOrUpdate(dialog);
    return dialog;
  }

}
