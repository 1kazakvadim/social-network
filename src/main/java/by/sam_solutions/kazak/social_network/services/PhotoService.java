package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Photo;
import java.util.List;

public interface PhotoService {

  void saveOrUpdate(Photo photo);

  Photo getById(Long id);

  List<Photo> getAll();

  List<Photo> getAllByProfileId(Long id, Integer page, Integer size);

  void deleteById(Long id);

  List<Photo> getAllByProfileId(Long id);

  Long countByProfileId(Long id);

}
