package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.dao.MessageDao;
import by.sam_solutions.kazak.social_network.entities.Message;
import by.sam_solutions.kazak.social_network.services.MessageService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

  private final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

  @Autowired
  private MessageDao messageDao;

  @Override
  @Transactional
  public void saveOrUpdate(Message message) {
    logger.debug("saveOrUpdate({})", message);
    messageDao.saveOrUpdate(message);
  }

  @Override
  @Transactional(readOnly = true)
  public Message getById(Long id) {
    logger.debug("get message by id = {}", id);
    return messageDao.getById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Message> getAllByDialogId(Long id) {
    return messageDao.getAllByDialogId(id);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    logger.debug("delete message with id = {}", id);
    messageDao.deleteById(id);
  }

}
