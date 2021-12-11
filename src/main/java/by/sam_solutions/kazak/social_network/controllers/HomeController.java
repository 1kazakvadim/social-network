package by.sam_solutions.kazak.social_network.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String defaultPageRedirect() {
    return "redirect:profile";
  }

}
