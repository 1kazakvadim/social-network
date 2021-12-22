package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.entities.BasicInformation;
import by.sam_solutions.kazak.social_network.entities.VerificationToken;
import by.sam_solutions.kazak.social_network.facades.UserFacade;
import by.sam_solutions.kazak.social_network.facades.VerificationTokenFacade;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

  @Autowired
  private UserFacade userFacade;

  @Autowired
  private VerificationTokenFacade verificationTokenFacade;

  @GetMapping("/register")
  public ModelAndView getRegisterPage(ModelAndView modelAndView) {
    BasicInformation basicInformation = new BasicInformation();
    modelAndView.addObject("basicInformation", basicInformation);
    modelAndView.setViewName("register");
    return modelAndView;
  }

  @PostMapping("/register")
  public ModelAndView registerUser(HttpServletRequest request, ModelAndView modelAndView,
      @ModelAttribute("basicInformation") BasicInformation basicInformation,
      @RequestParam("email") String email, @RequestParam("password") String password,
      @RequestParam("confirmPassword") String confirmPassword,
      @RequestParam(name = "termsAndConditions", defaultValue = "false") String termsAndConditions) {
    if (userFacade.isEmailExists(email)) {
      modelAndView.addObject("messageError", "E-mail already exists");
      modelAndView.setViewName("register");
      return modelAndView;
    }
    if (!userFacade.isPasswordMatchConfirmPassword(password, confirmPassword)) {
      modelAndView.addObject("messageError", "Passwords do not match");
      modelAndView.setViewName("register");
      return modelAndView;
    }
    if (termsAndConditions.equals("false")) {
      modelAndView.addObject("messageError", "Accept the terms and conditions");
      modelAndView.setViewName("register");
      return modelAndView;
    }
    userFacade.registerUserAndSendVerificationToken(basicInformation, email, password,
        request.getContextPath());
    modelAndView
        .addObject("messageSuccess", String.format("The link was sent to %s", email.toLowerCase()));
    modelAndView.setViewName("register");
    return modelAndView;
  }

  @GetMapping(value = "/confirm-register")
  public ModelAndView confirmRegisterUser(ModelAndView modelAndView,
      @RequestParam("token") String token) {
    VerificationToken verificationToken = verificationTokenFacade.getByToken(token);
    if (verificationToken == null) {
      modelAndView.addObject("messageError", "The link is invalid or broken");
      modelAndView.setViewName("login");
      return modelAndView;
    }
    if (verificationTokenFacade.isTokenExpired(token)) {
      modelAndView.addObject("messageError", "The token has expired");
      modelAndView.setViewName("login");
      return modelAndView;
    }
    userFacade.confirmRegisterUser(token);
    modelAndView.addObject("messageSuccess", "The confirmation is successful");
    modelAndView.setViewName("login");
    return modelAndView;
  }

}
