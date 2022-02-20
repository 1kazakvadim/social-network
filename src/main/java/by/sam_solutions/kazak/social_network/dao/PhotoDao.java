package by.sam_solutions.kazak.social_network.dao;

import by.sam_solutions.kazak.social_network.entities.Photo;
import java.util.List;

public interface PhotoDao extends IAbstractBaseDao<Photo> {

  List<Photo> getAllByProfileId(Long id, Integer page, Integer size);

  List<Photo> getAllByProfileId(Long id);

  Long countByProfileId(Long id);

}
