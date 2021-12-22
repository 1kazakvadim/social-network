package by.sam_solutions.kazak.social_network.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:security.properties")
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

  private static final String ROLE_ADMIN = "ADMIN";
  private static final String ROLE_USER = "USER";

  @Autowired
  private Environment environment;

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authProvider());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);
    http.authorizeRequests()
        .antMatchers("/profile/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
        .antMatchers("/users/**").hasAnyRole(ROLE_ADMIN)
        .antMatchers("/**").permitAll()
        .and().formLogin().loginProcessingUrl("/login").loginPage("/login")
        .defaultSuccessUrl("/profile")
        .and()
        .logout().logoutSuccessUrl("/login")
        .and()
        .rememberMe().key(environment.getRequiredProperty("security.rememberMe.key"))
        .userDetailsService(userDetailsService);
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers("/resources/static/css/**",
            "/resources/static/js/**", "/resources/static/images/**",
            "/resources/static/fonts/**");
  }

}
