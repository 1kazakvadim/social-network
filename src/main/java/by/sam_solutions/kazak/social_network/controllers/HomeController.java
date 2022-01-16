package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.entities.Profile;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  @GetMapping("/")
  public ModelAndView defaultPageRedirect(ModelAndView modelAndView, HttpServletRequest request,
      HttpServletResponse response) throws IOException {
    Profile profile = (Profile) request.getSession().getAttribute("profile");
    if (profile == null) {
      modelAndView.setViewName("login");
      return modelAndView;
    }
    response.sendRedirect(request.getContextPath() + "/id"+profile.getId());
    return null;
  }

}
