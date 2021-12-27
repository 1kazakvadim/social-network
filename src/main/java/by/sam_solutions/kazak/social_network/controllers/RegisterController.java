package by.sam_solutions.kazak.social_network.controllers;

import by.sam_solutions.kazak.social_network.dto.ProfileDTO;
import by.sam_solutions.kazak.social_network.entities.Token;
import by.sam_solutions.kazak.social_network.facades.UserFacade;
import by.sam_solutions.kazak.social_network.facades.TokenFacade;
import by.sam_solutions.kazak.social_network.validators.ProfileDtoValidator;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
  private TokenFacade tokenFacade;

  @Autowired
  private ProfileDtoValidator profileDtoValidator;

  @Autowired
  private MessageSource messageSource;

  @GetMapping("/register")
  public ModelAndView getRegisterPage(ModelAndView modelAndView) {
    ProfileDTO profileDTO = new ProfileDTO();
    modelAndView.addObject("profileDTO", profileDTO);
    modelAndView.setViewName("register");
    return modelAndView;
  }

  @PostMapping("/register")
  public ModelAndView registerUser(HttpServletRequest request, ModelAndView modelAndView,
      @ModelAttribute("profileDTO") ProfileDTO profileDTO, BindingResult result) {
    profileDtoValidator.validate(profileDTO, result);
    if (result.hasErrors()) {
      modelAndView.setViewName("register");
      return modelAndView;
    }
    userFacade.registerUserAndSendVerificationToken(profileDTO, request.getContextPath());
    modelAndView
        .addObject("messageSuccess",
            String.format(messageSource.getMessage("registerPage.success.linkToEmail", null, Locale
                .getDefault()) + " %s", profileDTO.getEmail().toLowerCase()));
    modelAndView.setViewName("register");
    return modelAndView;
  }

  @GetMapping(value = "/confirm-register")
  public ModelAndView confirmRegisterUser(ModelAndView modelAndView,
      @RequestParam("token") String token) {
    Token verificationToken = tokenFacade.getByToken(token);
    if (verificationToken == null) {
      modelAndView.addObject("messageError",
          messageSource.getMessage("token.error.invalidOrBroken", null, Locale
              .getDefault()));
      modelAndView.setViewName("login");
      return modelAndView;
    }
    if (tokenFacade.isTokenExpired(token)) {
      modelAndView.addObject("messageError",
          messageSource.getMessage("token.error.isExpired", null, Locale.getDefault()));
      modelAndView.setViewName("login");
      return modelAndView;
    }
    userFacade.confirmRegisterUser(token);
    modelAndView.addObject("messageSuccess",
        messageSource.getMessage("confirmation.success.confirmationSuccess", null, Locale
            .getDefault()));
    modelAndView.setViewName("login");
    return modelAndView;
  }

}
