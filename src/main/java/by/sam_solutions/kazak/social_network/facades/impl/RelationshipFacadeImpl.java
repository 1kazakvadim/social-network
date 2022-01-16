package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Relationship;
import by.sam_solutions.kazak.social_network.facades.RelationshipFacade;
import by.sam_solutions.kazak.social_network.services.RelationshipService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RelationshipFacadeImpl implements RelationshipFacade {

  @Autowired
  private RelationshipService relationshipService;

  @Override
  public List<Relationship> getAll() {
    return relationshipService.getAll();
  }

}
