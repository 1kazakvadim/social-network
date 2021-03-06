package by.sam_solutions.kazak.social_network.services.impl;

import by.sam_solutions.kazak.social_network.entities.User;
import by.sam_solutions.kazak.social_network.entities.UserPrincipal;
import by.sam_solutions.kazak.social_network.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  @Autowired
  private UserService userService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userService.getByEmail(email);
    if (user == null) {
      logger.info("User not found with email: {}", email);
      throw new UsernameNotFoundException(email);
    }
    return new UserPrincipal(user);
  }

}
