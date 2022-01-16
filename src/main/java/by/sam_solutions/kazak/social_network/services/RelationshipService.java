package by.sam_solutions.kazak.social_network.services;

import by.sam_solutions.kazak.social_network.entities.Relationship;
import java.util.List;

public interface RelationshipService {

  void saveOrUpdate(Relationship relationship);

  Relationship getById(Long id);

  List<Relationship> getAll();

  void deleteById(Long id);

}
