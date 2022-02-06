package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Dialog;
import by.sam_solutions.kazak.social_network.entities.Profile;
import java.util.List;

public interface DialogService {

  void saveOrUpdate(Dialog dialog);

  Dialog getById(Long id);

  Dialog getBySenderProfileIdAndRecipientProfileId(Long senderProfileId,
      Long recipientProfileId);

  List<Dialog> getAll();

  List<Dialog> getAllByProfileId(Long id);

  void deleteById(Long id);

  List<Profile> getProfilesWithDialogs(List<Dialog>dialogs, Long profileId);

}
