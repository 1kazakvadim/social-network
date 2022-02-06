package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.Message;
import java.util.List;

public interface MessageDao extends IAbstractBaseDao<Message> {

  List<Message> getAllByDialogId(Long id);

}
