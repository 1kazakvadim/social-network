package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.entities.Profile;
import by.sam_solutions.kazak.social_network.facades.ProfileFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

  @Autowired
  private ProfileFacade profileFacade;

  @GetMapping("/search")
  public ModelAndView search(ModelAndView modelAndView,
      @RequestParam(value = "search", required = false) String search) {
    if (search != null) {
      List<Profile> profiles = profileFacade.searchForProfiles(search);
      modelAndView.addObject("profiles", profiles);
    }
    modelAndView.setViewName("search");
    return modelAndView;
  }

}
