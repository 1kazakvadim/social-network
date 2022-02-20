package by.sam_solutions.kazak.social_network.converters;

import by.sam_solutions.kazak.social_network.dto.MessageDTO;
import by.sam_solutions.kazak.social_network.entities.Message;
import by.sam_solutions.kazak.social_network.services.DialogService;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.converters.TwoWayConverter;
import org.springframework.stereotype.Component;

@Component
public class MessageConverter implements TwoWayConverter {

  private static final Logger logger = LoggerFactory.getLogger(MessageConverter.class);

  @Autowired
  private ProfileService profileService;

  @Autowired
  private DialogService dialogService;

  @Override
  public Class getSourceClass() {
    return Message.class;
  }

  @Override
  public Class getTargetClass() {
    return MessageDTO.class;
  }

  @Override
  public Object convertTargetToSourceClass(Object target, Class sourceClass) throws Exception {
    if (target == null) {
      logger.debug("IllegalArgumentException {}", MessageConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s",
              MessageConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(sourceClass)
        || !this.getTargetClass().isAssignableFrom(target.getClass())) {
      logger.debug("IllegalArgumentException {}", MessageConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("Illegal arguments %s", MessageConverter.class.getName()));
    }
    MessageDTO targetMessage = (MessageDTO) target;
    Message sourceMessage = (Message) sourceClass.getDeclaredConstructor()
        .newInstance();
    sourceMessage.setId(targetMessage.getId());
    sourceMessage.setDialog(dialogService.getById(targetMessage.getDialogId()));
    sourceMessage.setMessageText(targetMessage.getMessageText());
    sourceMessage.setMessageSender(
        profileService.getById(targetMessage.getMessageSenderProfileId()));
    sourceMessage.setTimeCreation(targetMessage.getTimeCreation());
    return sourceMessage;
  }

  @Override
  public Object convertSourceToTargetClass(Object source, Class targetClass) throws Exception {
    if (source == null) {
      logger.debug("IllegalArgumentException {}", MessageConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s",
              ContactInformationConverter.class.getName()));
    }
    if (!this.getSourceClass().isAssignableFrom(source.getClass())
        || !this.getTargetClass().isAssignableFrom(targetClass)) {
      logger.debug("IllegalArgumentException {}", MessageConverter.class.getName());
      throw new IllegalArgumentException(
          String.format("IllegalArgumentException %s",
              ContactInformationConverter.class.getName()));
    }
    Message sourceMessage = (Message) source;
    MessageDTO targetMessage = (MessageDTO) targetClass.getDeclaredConstructor()
        .newInstance();
    targetMessage.setId(sourceMessage.getId());
    targetMessage.setDialogId(sourceMessage.getDialog().getId());
    targetMessage.setMessageText(sourceMessage.getMessageText());
    targetMessage.setMessageSenderProfileId(sourceMessage.getMessageSender().getId());
    targetMessage.setTimeCreation(sourceMessage.getTimeCreation());
    return targetMessage;
  }

}
