package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.services.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

  private final UserService userService;

  public TestController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/test")
  public List<User> homePage() {
    List<User> list = userService.getAll();
    return list;
  }

}
