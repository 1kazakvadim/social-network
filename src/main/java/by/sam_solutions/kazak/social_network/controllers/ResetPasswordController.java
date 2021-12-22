package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.facades.PasswordResetTokenFacade;
import by.sam_solutions.kazak.social_network.facades.UserFacade;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResetPasswordController {

  @Autowired
  private UserFacade userFacade;

  @Autowired
  private PasswordResetTokenFacade passwordResetTokenFacade;

  @GetMapping("/recover-password")
  public String getRecoverPasswordPage() {
    return "recover-password";
  }

  @PostMapping("/recover-password")
  public ModelAndView processRecoverPassword(HttpServletRequest request, ModelAndView modelAndView,
      @RequestParam("email") String email) {
    if (!userFacade.isEmailExists(email)) {
      modelAndView.addObject("messageError", "No such E-mail");
      modelAndView.setViewName("recover-password");
      return modelAndView;
    }
    passwordResetTokenFacade.createAndSendPasswordResetToken(email, request.getContextPath());
    modelAndView.addObject("messageSuccess", "E-mail was sent");
    modelAndView.setViewName("recover-password");
    return modelAndView;
  }

  @GetMapping(value = "/confirm-reset")
  public ModelAndView confirmResetPasswordToken(ModelAndView modelAndView,
      @RequestParam String token) {
    User user = passwordResetTokenFacade.getByToken(token).getUser();
    if (token == null) {
      modelAndView.addObject("messageError", "invalid token");
      modelAndView.setViewName("recover-password");
      return modelAndView;
    }
    if (user == null) {
      modelAndView.addObject("messageError", "invalid user");
      modelAndView.setViewName("recover-password");
      return modelAndView;
    }
    if (passwordResetTokenFacade.isTokenExpired(token)) {
      modelAndView.addObject("messageError", "The token has expired");
      modelAndView.setViewName("recover-password");
      return modelAndView;
    }
    modelAndView.setViewName("redirect:/reset-password/" + token);
    return modelAndView;
  }

  @GetMapping(value = "/reset-password/{token}")
  public ModelAndView getChangePasswordPage(ModelAndView modelAndView,
      @PathVariable("token") String token) {
    modelAndView.addObject("token", token);
    modelAndView.setViewName("reset-password");
    return modelAndView;
  }

  @PostMapping("/reset-password/{token}")
  public ModelAndView saveChangePassword(ModelAndView modelAndView,
      @PathVariable String token, @RequestParam("newPassword") String newPassword,
      @RequestParam("confirmPassword") String confirmPassword) {
    if (!userFacade.isPasswordMatchConfirmPassword(newPassword, confirmPassword)) {
      modelAndView.addObject("messageError", "Passwords do not match");
      modelAndView.setViewName("reset-password");
      return modelAndView;
    }
    passwordResetTokenFacade.resetUserPassword(token, newPassword);
    modelAndView.setViewName("redirect:/login");
    return modelAndView;
  }

}
