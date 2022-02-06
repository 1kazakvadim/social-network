package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.Dialog;
import java.util.List;

public interface DialogDao extends IAbstractBaseDao<Dialog> {

  List<Dialog> getAllByProfileId(Long id);

  Dialog getBySenderProfileIdAndRecipientProfileId(Long senderProfileId,
      Long recipientProfileId);

}
