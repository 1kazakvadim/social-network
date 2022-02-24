package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Dialog;
import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;

public interface DialogFacade {

  List<Dialog> getAllByProfileId(Long id);

  void deleteById(Long id);

  List<Profile> getProfilesWithDialogs(Long id);

  Dialog getBySenderProfileIdAndRecipientProfileId(Long senderProfileId, Long recipientProfileId);

  Dialog createDialog(Profile senderProfile, Profile recipientProfile);

}
