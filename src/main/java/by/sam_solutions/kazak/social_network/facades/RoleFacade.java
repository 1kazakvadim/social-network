package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.Role;
import java.util.List;

public interface RoleFacade {

  List<Role> getAll();

  void changeProfileRole(Profile profile, Long roleId);

}
