package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.converters.MessageConverter;
import by.sam_solutions.kazak.social_network.dto.MessageDTO;
import by.sam_solutions.kazak.social_network.entities.Message;
import by.sam_solutions.kazak.social_network.facades.MessageFacade;
import by.sam_solutions.kazak.social_network.services.MessageService;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageFacadeImpl implements MessageFacade {

  private static final Logger logger = LoggerFactory.getLogger(MessageFacadeImpl.class);

  @Autowired
  private MessageService messageService;

  @Autowired
  private MessageConverter messageConverter;

  @Override
  public List<Message> getAllByDialogId(Long id) {
    return messageService.getAllByDialogId(id);
  }

  @Override
  public void sendMessage(MessageDTO messageDTO) {
    Message message = new Message();
    try {
      message = (Message) messageConverter.convertTargetToSourceClass(messageDTO, Message.class);
    } catch (Exception exp) {
      logger.debug("Error converting {} to {}", MessageDTO.class.getName(),
          Message.class.getName());
      exp.printStackTrace();
    }
    message.setTimeCreation(LocalDateTime.now());
    messageService.saveOrUpdate(message);
  }

}
