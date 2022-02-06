package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.dto.MessageDTO;
import by.sam_solutions.kazak.social_network.entities.Message;
import java.util.List;

public interface MessageFacade {

  List<Message> getAllByDialogId(Long id);

  void sendMessage(MessageDTO messageDTO);

}
