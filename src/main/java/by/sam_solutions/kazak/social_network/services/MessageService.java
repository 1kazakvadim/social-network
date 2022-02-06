package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Message;
import java.util.List;

public interface MessageService {

  void saveOrUpdate(Message message);

  Message getById(Long id);

  List<Message> getAllByDialogId(Long id);

  void deleteById(Long id);

}
