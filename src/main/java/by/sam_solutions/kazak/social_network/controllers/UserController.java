package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.dto.UserDTO;
import by.sam_solutions.kazak.social_network.facades.UserFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

  @Autowired
  private UserFacade userFacade;

  @GetMapping("/users")
  public String users(Model model) {
    List<UserDTO> users = userFacade.getAll();
    model.addAttribute("users", users);
    return "user-list";
  }

}
