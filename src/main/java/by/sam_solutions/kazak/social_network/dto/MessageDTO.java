package by.sam_solutions.kazak.social_network.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.Size;

public class MessageDTO {

  private Long id;
  private Long dialogId;

  @Size(max = 255)
  private String messageText;

  private Long messageSenderProfileId;
  private LocalDateTime timeCreation;

  public MessageDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getDialogId() {
    return dialogId;
  }

  public void setDialogId(Long dialogId) {
    this.dialogId = dialogId;
  }

  public String getMessageText() {
    return messageText;
  }

  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }

  public Long getMessageSenderProfileId() {
    return messageSenderProfileId;
  }

  public void setMessageSenderProfileId(Long messageSenderProfileId) {
    this.messageSenderProfileId = messageSenderProfileId;
  }

  public LocalDateTime getTimeCreation() {
    return timeCreation;
  }

  public void setTimeCreation(LocalDateTime timeCreation) {
    this.timeCreation = timeCreation;
  }

}
