package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.entities.Role;
import by.sam_solutions.kazak.social_network.facades.RoleFacade;
import by.sam_solutions.kazak.social_network.services.ProfileService;
import by.sam_solutions.kazak.social_network.services.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleFacadeImpl implements RoleFacade {

  @Autowired
  private RoleService roleService;

  @Autowired
  private ProfileService profileService;

  @Override
  public List<Role> getAll() {
    return roleService.getAll();
  }

  @Override
  public void changeProfileRole(Profile profile, Long roleId) {
    profile.getUser().setRole(roleService.getById(roleId));
    profileService.saveOrUpdate(profile);
  }

}
