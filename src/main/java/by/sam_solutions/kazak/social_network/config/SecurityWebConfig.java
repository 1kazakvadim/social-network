package by.sam_solutions.kazak.social_network.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("mkyong").password("{noop}123456").roles("USER");
    auth.inMemoryAuthentication().withUser("admin").password("{noop}123456").roles("ADMIN");
    auth.inMemoryAuthentication().withUser("dba").password("{noop}123456").roles("DBA");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
        .antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
        .and().formLogin();

  }




//
//  @Override
//  public void configure(WebSecurity web) {
//    web.ignoring()
//        .antMatchers("/resources/**", "/static/**");
//  }

}
