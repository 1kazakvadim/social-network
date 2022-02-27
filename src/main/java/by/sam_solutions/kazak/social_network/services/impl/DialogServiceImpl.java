package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.DialogDao;
import by.sam_solutions.kazak.social_network.entities.Dialog;
import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.services.DialogService;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DialogServiceImpl implements DialogService {

  private static final Logger logger = LoggerFactory.getLogger(DialogServiceImpl.class);

  @Autowired
  private DialogDao dialogDao;

  @Override
  @Transactional
  public void saveOrUpdate(Dialog dialog) {
    logger.debug("saveOrUpdate({})", dialog);
    dialogDao.saveOrUpdate(dialog);
  }

  @Override
  @Transactional(readOnly = true)
  public Dialog getById(Long id) {
    logger.debug("get dialog by id = {}", id);
    return dialogDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Dialog getBySenderProfileIdAndRecipientProfileId(Long senderProfileId,
      Long recipientProfileId) {
    return dialogDao.getBySenderProfileIdAndRecipientProfileId(senderProfileId, recipientProfileId);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Dialog> getAllByProfileId(Long id) {
    return dialogDao.getAllByProfileId(id);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete dialog with id = {}", id);
    dialogDao.deleteById(id);
  }

  @Override
  public List<Profile> getProfilesWithDialogs(List<Dialog> dialogs, Long profileId) {
    List<Profile> profiles = new ArrayList<>();
    for (Dialog dialog : dialogs) {
      if (!dialog.getRecipientProfile().getId().equals(profileId)) {
        profiles.add(dialog.getRecipientProfile());
      }
      if (!dialog.getSenderProfile().getId().equals(profileId)) {
        profiles.add(dialog.getSenderProfile());
      }
    }
    return profiles;
  }

}
