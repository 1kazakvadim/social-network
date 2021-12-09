package by.sam_solutions.kazak.social_network.facades;

import by.sam_solutions.kazak.social_network.dto.UserDTO;
import java.util.List;

public interface UserFacade {

  List<UserDTO> getAll();

  UserDTO findByEmail(String email);

}
