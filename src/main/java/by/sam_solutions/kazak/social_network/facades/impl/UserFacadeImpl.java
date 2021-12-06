package by.sam_solutions.kazak.social_network.facades.impl;

import by.sam_solutions.kazak.social_network.converters.UserConverter;
import by.sam_solutions.kazak.social_network.dto.UserDTO;
import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.facades.UserFacade;
import by.sam_solutions.kazak.social_network.services.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

  private final UserService userService;
  private final UserConverter userConverter;

  public UserFacadeImpl(UserService userService, UserConverter userConverter) {
    this.userService = userService;
    this.userConverter = userConverter;
  }

  @Override
  public List<UserDTO> getAll() {
    List<UserDTO> usersDTO = new ArrayList<>();
    List<User> users = userService.getAll();
    for (User user : users) {
      try {
        usersDTO.add((UserDTO) userConverter.convertSourceToTargetClass(user, UserDTO.class));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return usersDTO;
  }

}
