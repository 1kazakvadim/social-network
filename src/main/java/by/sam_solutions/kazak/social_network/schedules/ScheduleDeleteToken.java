package by.sam_solutions.kazak.social_network.schedules;

import by.sam_solutions.kazak.social_network.entities.Token;
import by.sam_solutions.kazak.social_network.services.TokenService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ScheduleDeleteToken {

  private static final Logger logger = LoggerFactory.getLogger(ScheduleDeleteToken.class);

  @Autowired
  private ThreadPoolTaskScheduler threadPoolTaskScheduler;

  @Autowired
  private TokenService tokenService;

  @Scheduled(cron = "${cron.expression}")
  @Transactional
  public void scheduleDeleteToken() {
    List<Token> tokens = tokenService.getAll();
    if (tokens.stream().noneMatch(token -> tokenService.isTokenExpired(token.getToken()))) {
      logger.debug("Schedule delete token ended");
      stopAll();
    }
    for (Token token : tokens) {
      if (tokenService.isTokenExpired(token.getToken())) {
        logger.debug("Schedule delete token with id = {}", token.getId());
        tokenService.deleteById(token.getId());
      }
    }
  }

  private void stopAll() {
    threadPoolTaskScheduler.shutdown();
  }

}
