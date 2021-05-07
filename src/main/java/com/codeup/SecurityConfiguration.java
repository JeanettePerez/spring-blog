package com.codeup;

import com.codeup.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private UserDetailsLoader userLoader;

  public SecurityConfiguration(UserDetailsLoader userLoader) {
    this.userLoader = userLoader;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userLoader)
      .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/posts")
          .permitAll()
      .and()
          .logout()
          .logoutSuccessUrl("/login?logout")
      .and()
          .authorizeRequests()
          .antMatchers("/","/posts")
          .permitAll()
      .and()
          .authorizeRequests()
          .antMatchers(
            "/post/create",
            "/posts/update/{id}"
          )
      .authenticated();
  }
}
